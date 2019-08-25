package tmg.utilities.about

import android.os.Parcel
import android.os.Parcelable

data class AboutThisAppDependency(
    val order: Int,
    val dependencyName: String,
    val author: String,
    val imageUrl: String,
    val url: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(order)
        parcel.writeString(dependencyName)
        parcel.writeString(author)
        parcel.writeString(imageUrl)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return dependencyName.hashCode() + author.hashCode() + imageUrl.hashCode() + url.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<AboutThisAppDependency> {
        override fun createFromParcel(parcel: Parcel): AboutThisAppDependency {
            return AboutThisAppDependency(parcel)
        }

        override fun newArray(size: Int): Array<AboutThisAppDependency?> {
            return arrayOfNulls(size)
        }
    }

}