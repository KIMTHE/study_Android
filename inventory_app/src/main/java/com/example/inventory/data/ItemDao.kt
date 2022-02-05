package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    //기본 키가 이미 데이터베이스에 있으면 새 항목을 무시합니다.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item where id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * FROM item order by name ASC")
    fun getItems(): Flow<List<Item>>
}