package com.gudino.clasificados_ml.model

import android.os.Parcel
import android.os.Parcelable

data class Classified(val id: String, val site_id: String, val title: String, val price: Double, val currency_id: String,
                      val available_quantity: Int, val sold_quantity: Int, val condition: String, val thumbnail: String,
                      val accepts_mercadopago: Boolean, val pictures: List<Picture>) : Parcelable {

    constructor(parcel: Parcel) : this(
            id = parcel.readString(),
            site_id = parcel.readString(),
            title = parcel.readString(),
            price = parcel.readDouble(),
            currency_id = parcel.readString(),
            available_quantity = parcel.readInt(),
            sold_quantity = parcel.readInt(),
            condition = parcel.readString(),
            thumbnail = parcel.readString(),
            accepts_mercadopago = parcel.readByte() != 0.toByte(),
            pictures = arrayListOf<Picture>().apply {
                parcel.readList(this, Picture::class.java.classLoader)
            })

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(site_id)
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(currency_id)
        parcel.writeInt(available_quantity)
        parcel.writeInt(sold_quantity)
        parcel.writeString(condition)
        parcel.writeString(thumbnail)
        parcel.writeByte(if (accepts_mercadopago) 1 else 0)
        parcel.writeList(pictures)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Classified> {
        override fun createFromParcel(parcel: Parcel): Classified {
            return Classified(parcel)
        }

        override fun newArray(size: Int): Array<Classified?> {
            return arrayOfNulls(size)
        }
    }
}