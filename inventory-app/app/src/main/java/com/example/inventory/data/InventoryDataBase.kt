package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDataBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile // Almacenar los cambios en memoria principal y no en caché
        private var Instance: InventoryDataBase? = null

        fun getDatabase(context: Context): InventoryDataBase {
            return Instance ?: synchronized(this) {
               Room.databaseBuilder(context, InventoryDataBase::class.java, "item_database")
                    .fallbackToDestructiveMigration().build().also {
                        Instance = it
                    }
            }
        }

    }
}