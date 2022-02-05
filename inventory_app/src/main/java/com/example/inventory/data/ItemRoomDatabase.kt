package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        //캐시되지 않고 모든 쓰기와 읽기는 기본 메모리에서 실행됩니다.
        //INSTANCE 값이 항상 최신 상태로 유지되고 모든 실행 스레드에서 같은지 확인할 수 있습니다.
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {
            //한 번에 한 실행 스레드만 이 코드 블록에 들어갈 수 있으므로,
            // 데이터베이스가 한 번만 초기화됩니다.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }
}