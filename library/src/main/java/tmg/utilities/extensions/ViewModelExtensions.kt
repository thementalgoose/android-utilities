package tmg.utilities.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//region Lifecycle

@Deprecated("Shortcut method will be removed.", ReplaceWith("viewModelScope.launch { }"))
fun ViewModel.async(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(block = block)
}

//endregion