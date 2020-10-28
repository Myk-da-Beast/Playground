package com.myk.library.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.ItemLocalDataModel
import com.myk.library.data.model.PokemonLocalDataModel
import com.myk.library.data.model.TypeLocalDataModel

@Database(
    entities = [
        PokemonLocalDataModel::class,
        ItemLocalDataModel::class,
        TypeLocalDataModel::class,
    ],
    version = 6
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun itemDao(): ItemDao
}

class Converters {
    @TypeConverter
    fun fromString(stringListString: String?) = stringListString?.split(",")?.map { it }

    @TypeConverter
    fun toString(stringList: List<String>?) = stringList?.joinToString(separator = ",")
}
