package com.lintang.second.main.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueAdapterItem(val badge: Int, val leagueName: String, val id: String) : Parcelable