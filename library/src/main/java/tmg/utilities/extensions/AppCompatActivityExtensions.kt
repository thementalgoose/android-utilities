package tmg.utilities.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlin.reflect.KClass

/**
 * View a URL in the Activity
 * @param url The url to view
 */
fun AppCompatActivity.viewUrl(url: String) {
    val intent: Intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

//endregion

//region Logging

/**
 * Quickly log an info statement - Will use the package name of the activity as the tag
 */
fun AppCompatActivity.logInfo(msg: String, tag: String = this.packageName.takeLast(20)) {
    Log.i(tag, this.localClassName + " - " + msg)
}

/**
 * Quickly log a debug statement - Will use the package name of the activity as the tag
 */
fun AppCompatActivity.logDebug(msg: String, tag: String = this.packageName.takeLast(20)) {
    Log.d(tag, this.localClassName + " - " + msg)
}

/**
 * Quickly log an error - Will use the package name of the activity as the tag
 */
fun AppCompatActivity.logError(msg: String, tag: String = this.packageName.takeLast(20)) {
    Log.e(tag, this.localClassName + " - " + msg)
}

//endregion

//region Toolbar

/**
 * Initialise a toolbar in an activity
 *
 * @param toolbarRes Resource id of the toolbar
 * @param showBack Should show the back arrow or not. Defaults to false
 * @param indicator Should override the back indicator.
 */
fun AppCompatActivity.initToolbar(@IdRes toolbarRes: Int, showBack: Boolean = false, @DrawableRes indicator: Int? = null): Toolbar {
    val toolbar = findViewById<Toolbar>(toolbarRes)
    setSupportActionBar(toolbar)
    if (showBack) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }
    indicator?.let {
        supportActionBar!!.setHomeAsUpIndicator(it)
    }
    return toolbar
}

/**
 * Set the action bar title
 * @param title String res of the title
 */
fun AppCompatActivity.setToolbarTitle(@StringRes title: Int) {
    supportActionBar?.setTitle(title)
}


/**
 * Set the action bar title
 * @param title String value of the title
 */
fun AppCompatActivity.setToolbarTitle(title: String) {
    supportActionBar?.title = title
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
fun AppCompatActivity.loadFragment(frag: Fragment, @IdRes layoutRes: Int, tag: String?) {
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
fun AppCompatActivity.loadFragment(frag: Fragment, @IdRes layoutRes: Int) {
    loadFragment(frag, layoutRes, null)
}

//endregion