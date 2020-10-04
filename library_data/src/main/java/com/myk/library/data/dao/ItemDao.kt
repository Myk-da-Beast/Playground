package com.myk.library.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myk.library.data.model.ItemLocalDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemLocalDataModel")
    fun getAll(): Flow<List<ItemLocalDataModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemLocalDataModel)

    suspend fun insertAll(item: List<ItemLocalDataModel>) {
        item.forEach {
            insert(it)
        }
    }
}
