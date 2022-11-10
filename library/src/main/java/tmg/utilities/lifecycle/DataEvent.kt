package tmg.utilities.lifecycle

@Deprecated(
    message = "The usage of single time events to the UI layer is discouraged as of https://developer.android.com/topic/architecture/ui-layer/events. In the event you do need this functionality, please use SingleLiveEvent",
    replaceWith = ReplaceWith("SingleLiveEvent"),
    level = DeprecationLevel.WARNING
)
data class DataEvent<T>(val data: T): Event()