package tmg.utilities.extensions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

/**
 * Start an activity from the class definition of an activity
 *
 * @param context The context that the activity is started from
 */
fun <T: AppCompatActivity> KClass<T>.startActivity(context: Context) {
    val intent: Intent = Intent(context, this.java)
    context.startActivity(intent)
}