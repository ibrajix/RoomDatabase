package com.ibrajix.roomdatabase.repository

import com.ibrajix.roomdatabase.data.UserDao
import com.ibrajix.roomdatabase.model.User
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepo @Inject constructor(private val userDao: UserDao) {


    //insert user details to room
    suspend fun createUserRecords(user: User) : Long {
        return userDao.insertToRoomDatabase(user)
    }

    //get single user details e.g with id 1
    val getUserDetails: Flow<List<User>> get() =  userDao.getUserDetails()

    //delete single user record
    suspend fun deleteSingleUserRecord() {
        userDao.deleteSingleUserDetails(1)
    }


}