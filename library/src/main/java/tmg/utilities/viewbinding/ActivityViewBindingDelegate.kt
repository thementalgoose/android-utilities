package tmg.utilities.viewbinding

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<T: ViewBinding>(
    private val inflater: ViewInflater<T>
): ReadOnlyProperty<AppCompatActivity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        binding?.let { return it }

        val layout = inflater.inflate(thisRef.layoutInflater)
        thisRef.setContentView(layout.root)
        return layout.also {
            binding = it
        }
    }
}