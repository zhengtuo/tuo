package com.zt.server

import android.os.Parcel
import android.os.Parcelable

class ServerBean() : Parcelable {

    var id: Int = 1
    var name: String = "Drelovey"

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "ServerBean(id=$id, name='$name')"
    }

    companion object CREATOR : Parcelable.Creator<ServerBean> {
        override fun createFromParcel(parcel: Parcel): ServerBean {
            return ServerBean(parcel)
        }

        override fun newArray(size: Int): Array<ServerBean?> {
            return arrayOfNulls(size)
        }
    }

}