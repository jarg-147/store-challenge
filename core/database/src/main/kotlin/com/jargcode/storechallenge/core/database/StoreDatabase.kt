package com.jargcode.storechallenge.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jargcode.storechallenge.core.database.dao.CartDao
import com.jargcode.storechallenge.core.database.model.CartItemEntity

@Database(
    entities = [
        CartItemEntity::class
    ],
    version = 1
)
internal abstract class StoreDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

}
