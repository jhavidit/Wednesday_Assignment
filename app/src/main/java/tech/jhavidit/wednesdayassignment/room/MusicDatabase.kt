package tech.jhavidit.wednesdayassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.jhavidit.wednesdayassignment.models.MusicItemLocal
import tech.jhavidit.wednesdayassignment.models.PreviousSearch

@Database(version = 2,entities = [MusicItemLocal::class,PreviousSearch::class],exportSchema = false)
abstract class MusicDatabase : RoomDatabase(){

    abstract fun musicDao():MusicDao

    companion object
    {
        @Volatile
        private var INSTANCE: MusicDatabase? = null

        fun getDatabase(context: Context): MusicDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicDatabase::class.java,
                    "country_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}