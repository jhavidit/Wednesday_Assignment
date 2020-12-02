package tech.jhavidit.wednesdayassignment.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MusicItem(
    val resultCount: Int = 0,
    val results: List<MusicResult>
) : Parcelable

@Parcelize
data class MusicResult(
    val kind : String = "",
    val artistName : String = "",
    val artworkUrl100  : String = "",
    val country : String = "",
    val trackPrice : String = "",
    val trackName : String = "",
    val collectionName : String = "",
    val currency : String = ""
) : Parcelable