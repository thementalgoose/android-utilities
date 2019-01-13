package tmg.utilities.extensions

import java.util.*

//region Date

fun Long.toDate(): Date {
    return Date(this)
}

//endregion