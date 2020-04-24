package tmg.utilities.lifecycle.common

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import tmg.utilities.extensions.initToolbar
import tmg.utilities.extensions.startActivity
import tmg.utilities.lifecycle.mvvm.MVVMActivity

/**
 * Common class which includes generic helpers (but no Rx or MVVM logic)
 * See [RxActivity] and [MVVMActivity] for those implementations
 */
abstract class CommonActivity: AppCompatActivity() {

    var toolbar: Toolbar? = null
    private var showBack: Boolean? = null

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
        setContentView(layoutId())
        initComponents()
        initViews()
    }

    //region Open / Abstract methods

    /**
     * Layout resource id
     */
    @LayoutRes
    abstract fun layoutId(): Int

    /**
     * Argument intercept method. To handle argument imports to the project
     *   Override to import arguments
     */
    open fun arguments(bundle: Bundle) { }

    /**
     * Any component initialisation logic (ie. View Models)
     * Runs before the [initViews] method
     */
    open fun initComponents() { }

    /**
     * Any view initialisation logic
     */
    open fun initViews() { }

    /**
     * Request permissions code
     */
    open val requestPermissionCode: Int = 1001

    //endregion

    //region Toolbar

    /**
     * Assign the toolbar to the item stored in the activity
     */
    fun setupToolbar(@IdRes toolbarRes: Int, showBack: Boolean = false, @DrawableRes backIcon: Int? = null) {
        this.toolbar = this.initToolbar(toolbarRes, showBack, backIcon ?: -1)
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
    fun requestAndroidPermissions(permissions: List<String>, requestCode: Int = requestPermissionCode) {
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
    fun requestAndroidPermission(permission: String, requestCode: Int = requestPermissionCode) {
        requestAndroidPermissions(listOf(permission), requestCode)
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
        if (requestCode == requestPermissionCode && permissions.isNotEmpty()) {
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
}