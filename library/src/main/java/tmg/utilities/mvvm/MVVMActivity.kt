package tmg.utilities.mvvm

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.Disposable
import tmg.utilities.utils.ColorUtils

abstract class MVVMActivity<VM: MVVMViewModel>: AppCompatActivity() {

    lateinit var viewModel: VM
    val disposables: MutableList<Disposable> = mutableListOf()
    var toolbar: Toolbar? = null

    private var showBack: Boolean? = null

    /**
     * OnCreate Method
     * - Will run arguments(bundle) in the appropriate time
     * - Apply view
     * - Set the VM
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle: Bundle? = intent.extras
        bundle?.let {
            if (savedInstanceState != null) {
                it.putAll(savedInstanceState)
            }
        }
        bundle?.let {
            arguments(it)
        }
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        viewModel = viewModelProvider().get(viewModelClass())
        viewModel.supplyContext(applicationContext)
        initViews()
    }
    open fun viewModelProvider(): ViewModelProvider {
        return ViewModelProviders.of(this)
    }

    /**
     * Argument intercept method. To handle argument imports to the project
     *   Override to import arguments
     */
    open fun arguments(bundle: Bundle) {
    }

    /**
     * Initialise the views
     */
    open fun initViews() {
    }

    /**
     * Initialise the toolbar
     */
    fun initToolbar(@IdRes toolbarRes: Int, showBack: Boolean = false, @DrawableRes indicator: Int? = null) {
        this.showBack = showBack
        toolbar = findViewById(toolbarRes)
        setSupportActionBar(toolbar)
        if (showBack) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeButtonEnabled(true)
        }
        indicator?.let {
            supportActionBar!!.setHomeAsUpIndicator(it)
        }
    }

    fun setToolbarTitle(@StringRes title: Int) {
        supportActionBar?.setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        showBack?.let {
            if (item!!.itemId == android.R.id.home) {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * OnResume
     * - Observe outputs of the viewmodel
     */
    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    /**
     * OnPause method
     * - Clean up observables
     */
    override fun onPause() {
        super.onPause()
        if (disposeOnPause()) {
            disposables.forEach { it.dispose() }
        }
    }

    open fun disposeOnPause(): Boolean {
        return true
    }

    /**
     * State restoration methods for passing data to the view models
     * - onSaveInstanceState
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            viewModel.saveInstanceState(it)
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * State restoration methods for passing data to the view models
     * - onRestoreInstanceState
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            viewModel.restoreInstanceState(it)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }

    //region Abstract methods

    /**
     * Abstract method to get the layout ID.
     *   Will be used in the onCreate to handle layout inflation
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Get the class of the VM for VM inflation
     */
    abstract fun viewModelClass(): Class<VM>

    /**
     * Initialise the views.
     * Kotlin Extensions should mean views are available in this method!
     */
    abstract fun observeViewModel()

    //endregion

    //region Status bar

    /**
     * Set the status bar colour manually
     */
    fun setStatusBar(@ColorInt col: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ColorUtils.darken(col)
        }
    }

    //endregion

    //region Permissions


    /**
     * Request a given permission
     * - Callbacks will be fired below under permissionDenied, permissionGranted, or permissionShowRational
     *
     * @param permissions List of permissions to grant.
     * @param requestCode Optional request code override
     */
    fun requestAndroidPermissions(permissions: List<String>, requestCode: Int = PERMISSION_REQUEST_CODE) {
        val permissionGranted = permissions.filter {
            ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
        if (permissionGranted.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), requestCode)
        }
    }

    /**
     * Request a specific permission
     *
     * @param permission The permission to request
     * @param requestCode Optional request code override
     */
    fun requestAndroidPermission(permission: String, requestCode: Int = PERMISSION_REQUEST_CODE) {
        return requestAndroidPermissions(listOf(permission), requestCode)
    }

    /**
     * Request Permission result intercept
     *
     * When running `requestPermission(permission)` we intercept it here and run the
     * - permissionGranted(list)
     * - permissionDenied(list)
     * - permissionShowRational(list)
     *
     * @param requestCode request code for the permission result
     * @param permissions Array containing the list of permissions that have been granted
     * @param grantResults Array containing a list of the permission statuses that have been requested
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE && permissions.isNotEmpty()) {
            if (grantResults.isNotEmpty()) {
                val results = permissions.mapIndexed { index, value ->
                    Pair(value, grantResults[index])
                }

                val granted = results
                    .filter { (_, index) -> index == PackageManager.PERMISSION_GRANTED }
                    .map { it.first }
                val rational = results
                    .filter { (_, index) -> index != PackageManager.PERMISSION_GRANTED }
                    .filter { (perm, _) ->
                        ActivityCompat.shouldShowRequestPermissionRationale(this, perm)
                    }
                    .map { it.first }
                val denied = results
                    .filter { (perm, _) ->
                        !granted.contains(perm) && !rational.contains(perm)
                    }
                    .map { it.first }

                if (granted.isNotEmpty()) {
                    permissionGranted(granted)
                }
                if (rational.isNotEmpty()) {
                    permissionShowRational(rational)
                }
                if (denied.isNotEmpty()) {
                    permissionDenied(denied)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * Method fired when a permission is denied
     *
     * @param perm List of permission strings that has been denied
     */
    open fun permissionDenied(perm: List<String>) {

    }

    /**
     * Method fired when a permission is granted
     *
     * @param perm List of permission strings that has been granted
     */
    open fun permissionGranted(perm: List<String>) {

    }

    /**
     * Method fired when a permission should show rational
     *
     * @param perm List of permission strings that requires rational
     */
    open fun permissionShowRational(perm: List<String>) {

    }

    //endregion

    //region Fragments


    /**
     * Loading in a fragment to an activity
     *
     * @param frag The fragment to load into the activity
     * @param layoutRes The resource id of the layout that the fragment should be loaded in
     * @param tag The tag that the fragment is attached to the view with
     */
    fun loadFragment(frag: Fragment, @IdRes layoutRes: Int, tag: String?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (tag != null) {
            transaction.replace(layoutRes, frag, tag)
        }
        else {
            transaction.replace(layoutRes, frag)
        }
        transaction.commit()
    }

    /**
     * Loading in a fragment to an activity
     *
     * @param frag The fragment to load into the activity
     * @param layoutRes The resource id of the layout that the fragment should be loaded in
     */
    fun loadFragment(frag: Fragment, @IdRes layoutRes: Int) {
        loadFragment(frag, layoutRes, null)
    }

    //endregion

    companion object {
        private const val PERMISSION_REQUEST_CODE: Int = 1001
    }
}