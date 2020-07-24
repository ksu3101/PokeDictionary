package com.swkang.model.domain.pokesearch.datas

import android.os.Parcel
import android.os.Parcelable

data class PokemonMapCoordinates(
    val name: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonMapCoordinates> {
        override fun createFromParcel(parcel: Parcel): PokemonMapCoordinates {
            return PokemonMapCoordinates(parcel)
        }

        override fun newArray(size: Int): Array<PokemonMapCoordinates?> {
            return arrayOfNulls(size)
        }
    }
}