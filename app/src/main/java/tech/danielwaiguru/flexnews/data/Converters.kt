package tech.danielwaiguru.flexnews.data

import androidx.room.TypeConverter
import tech.danielwaiguru.flexnews.models.Source

class Converters {
    @TypeConverter
    fun sourceToString(source: Source): String
    {
        return source.name
    }
    @TypeConverter
    fun stringToSource(name: String): Source
    {
        return Source(name, name)
    }

}