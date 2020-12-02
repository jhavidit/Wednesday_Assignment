package tech.jhavidit.wednesdayassignment.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "music_list")
@Parcelize
data class MusicItemLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val kind: String = "",
    val artistName: String = "",
    val artworkUrl100: String = "",
    val country: String = "",
    val trackPrice: String = "",
    val trackName: String = "",
    val collectionName: String = "",
    val currency: String = ""
) : Parcelable