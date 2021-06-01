package com.ibrajix.roomdatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ibrajix.roomdatabase.data.UserDao
import com.ibrajix.roomdatabase.global.ApplicationScope
import com.ibrajix.roomdatabase.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [User::class],  version = 2)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    class Callback @Inject constructor(private val songDatabase: Provider<UserDatabase>, @ApplicationScope private val applicationScope: CoroutineScope) : androidx.room.RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = songDatabase.get().userDao()
            applicationScope.launch {

            }
        }
    }


}