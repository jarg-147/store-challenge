package com.jargcode.storechallenge.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jargcode.storechallenge.core.database.dao.CartDao
import com.jargcode.storechallenge.core.database.model.CartProductEntity

@Database(
    entities = [
        CartProductEntity::class
    ],
    version = 1
)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

}
