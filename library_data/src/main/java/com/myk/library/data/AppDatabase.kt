package com.myk.library.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myk.library.data.dao.ItemDao
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.ItemLocalDataModel
import com.myk.library.data.model.PokemonLocalDataModel

@Database(entities = [PokemonLocalDataModel::class, ItemLocalDataModel::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun itemDao(): ItemDao
}
