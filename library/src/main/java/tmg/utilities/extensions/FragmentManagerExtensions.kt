package tmg.utilities.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Get the latest tag from a container
 * @param id The id of the container holding the fragment
 */
fun FragmentManager.latestTag(@IdRes id: Int): String? {
    return findFragmentById(id)?.tag
}