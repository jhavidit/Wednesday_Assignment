package tech.jhavidit.wednesdayassignment.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "previous_search",indices = [Index(value=["name"],unique = true)])
@Parcelize
data class PreviousSearch(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String
) : Parcelable
