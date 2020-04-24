package tmg.utilities.lifecycle

data class DataEvent<T>(val data: T): Event()

open class Event {
    var processEvent: Boolean = true
        private set
        get() {
            val current = field
            processEvent = false
            return current
        }
}