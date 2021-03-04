package tmg.utilities.lifecycle

open class Event {
    var processEvent: Boolean = true
        private set
        get() {
            val current = field
            processEvent = false
            return current
        }
}