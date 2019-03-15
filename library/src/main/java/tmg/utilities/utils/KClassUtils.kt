package tmg.utilities.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlin.reflect.KClass

fun <T: Any> KClass<T>.start(context: Context, with: ((bundle: Bundle) -> Unit)? = null) {
    val intent = Intent(context, this.java)
    if (with != null) {
        val bundle = Bundle()
        with(bundle)
        intent.putExtras(bundle)
    }
    context.startActivity(intent)
}