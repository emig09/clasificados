package com.gudino.clasificados_ml.model

import android.os.Parcel
import android.os.Parcelable

data class Picture(val secure_url: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) = parcel.writeString(secure_url)

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Picture> {
        override fun createFromParcel(parcel: Parcel): Picture {
            return Picture(parcel)
        }

        override fun newArray(size: Int): Array<Picture?> {
            return arrayOfNulls(size)
        }
    }

}