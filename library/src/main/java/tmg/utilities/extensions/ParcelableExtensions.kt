package tmg.utilities.extensions

import android.os.Parcel

fun Parcel.writeNullableInt(int: Int?) = writeNullable(int) { i ->
    writeInt(i)
}

fun Parcel.readNullableInt(): Int? = readNullable {
    readInt()
}

fun <T> Parcel.writeNullable(value: T?, write: Parcel.(T) -> Unit) {
    writeByte(if (value == null) 0 else 1)
    if (value != null) {
        write(value)
    }
}

fun <T> Parcel.readNullable(read: Parcel.() -> T): T? {
    val isNull = readByte() == 0.toByte()
    return if (!isNull) {
        read()
    } else {
        null
    }
}