package com.myk.library.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.myk.library.data.model.PokemonLocalDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonLocalDataModel")
    fun getAll(): Flow<List<PokemonLocalDataModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: PokemonLocalDataModel)

    @Transaction
    suspend fun insertAll(pokemon: List<PokemonLocalDataModel>) {
        pokemon.forEach {
            insert(it)
        }
    }

    @Transaction
    suspend fun clearDatabaseAndInsertNew(pokemon: List<PokemonLocalDataModel>) {
        clearAll()
        insertAll(pokemon)
    }

    @Query("SELECT * FROM PokemonLocalDataModel")
    fun pagingSource(): PagingSource<Int, PokemonLocalDataModel>

    @Transaction
    @Query("DELETE FROM PokemonLocalDataModel")
    suspend fun clearAll()
}
