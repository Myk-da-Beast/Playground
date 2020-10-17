package com.myk.library.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.myk.library.data.model.ItemLocalDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemLocalDataModel")
    fun getAll(): Flow<List<ItemLocalDataModel>>

    @Query("SELECT * FROM ItemLocalDataModel")
    fun getPage(): PagingSource<Int, ItemLocalDataModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ItemLocalDataModel)

    @Transaction
    suspend fun insertAll(item: List<ItemLocalDataModel>) {
        item.forEach {
            insert(it)
        }
    }

    @Transaction
    @Query("DELETE FROM ItemLocalDataModel")
    suspend fun clearAll()

    @Transaction
    suspend fun clearDatabaseAndInsertNew(items: List<ItemLocalDataModel>) {
        clearAll()
        insertAll(items)
    }
}
