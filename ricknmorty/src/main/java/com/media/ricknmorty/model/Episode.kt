package com.media.ricknmorty.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.media.base.view.displayable.DisplayableItem
import com.media.base.view.displayable.DisplayableItemViewType
import java.util.*

// Apparently @Parcelize doesn't work with inheritance, had to generate implementation and make fields nullable
data class Episode(
        val id: Int = -1,
        val name: String? = "",
        @SerializedName("air_date")
        val airDate: String? = "",
        @SerializedName("episode")
        val code: String? = "",
        val characters: ArrayList<String>? = arrayListOf(),
        val url: String? = "",
        val created: String? = ""
) : DisplayableItem, Parcelable {

    override val viewType: DisplayableItemViewType
        get() = DisplayableItemViewType.EPISODE

    override fun areItemsTheSame(other: DisplayableItem): Boolean = (other as? Episode)?.id == id

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(airDate)
        parcel.writeString(code)
        parcel.writeStringList(characters)
        parcel.writeString(url)
        parcel.writeString(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Episode> {
        override fun createFromParcel(parcel: Parcel): Episode {
            return Episode(parcel)
        }

        override fun newArray(size: Int): Array<Episode?> {
            return arrayOfNulls(size)
        }
    }
}