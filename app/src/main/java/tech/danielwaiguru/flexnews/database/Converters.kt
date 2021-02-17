package tech.danielwaiguru.flexnews.database

import androidx.room.TypeConverter
import tech.danielwaiguru.flexnews.models.Source

class Converters {
    @TypeConverter
    fun sourceToString(source: Source): String = source.name

    @TypeConverter
    fun stringToSource(name: String): Source =  Source(name)
}