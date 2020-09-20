package com.myk.library.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myk.library.data.model.PokemonLocalDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonLocalDataModel")
    fun getAll(): Flow<List<PokemonLocalDataModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: PokemonLocalDataModel)

    suspend fun insertAll(pokemon: List<PokemonLocalDataModel>) {
        pokemon.forEach {
            insert(it)
        }
    }
}
