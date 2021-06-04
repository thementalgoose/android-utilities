package tmg.utilities.viewbinding

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T: ViewBinding>(
    private val binder: ViewBinder<T>,
): ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        thisRef.viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                binding = null
            }
        })

        if (!thisRef.viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw RuntimeException("Attempting to access a binding before onCreate")
        }

        if (thisRef.view == null) {
            throw RuntimeException("Fragment is initialised but the view is null, and therefore we cannot bind the binding")
        }
        return binder.bind(thisRef.requireView()).also {
            binding = it
        }
    }
}