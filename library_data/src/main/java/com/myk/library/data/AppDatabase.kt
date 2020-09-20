package com.myk.library.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myk.library.data.dao.PokemonDao
import com.myk.library.data.model.PokemonLocalDataModel

@Database(entities = [PokemonLocalDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
