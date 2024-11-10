package com.jargcode.storechallenge.core.testing

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.jargcode.storechallenge.core.database.StoreDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.*
import javax.inject.Inject

abstract class StoreAndroidTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: StoreDatabase

    protected lateinit var context: Context

    @Before
    open fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        hiltRule.inject()
        db.clearAllTables()
    }

    @After
    open fun tearDown() {
        db.close()
    }

}