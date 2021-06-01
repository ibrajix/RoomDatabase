package com.ibrajix.roomdatabase.data

import androidx.room.*
import com.ibrajix.roomdatabase.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /**
     * CREATE
     */

    //insert data to room database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToRoomDatabase(user: User) : Long

    /**
     * READ
     */

    //get all users inserted to room database...normally this is supposed to be a list of users
    @Transaction
    @Query("SELECT * FROM users_table ORDER BY id DESC")
    fun getUserDetails() : Flow<List<User>>

    //get single user inserted to room database
    @Transaction
    @Query("SELECT * FROM users_table WHERE id = :id ORDER BY id DESC")
    fun getSingleUserDetails(id: Long) : Flow<User>

    /**
     * UPDATE
     */

    //update user details
    @Update
    suspend fun updateUserDetails(user: User)

    /**
     * DELETE
     */

    //delete single user details
    @Query("DELETE FROM users_table WHERE id = :id")
    suspend fun deleteSingleUserDetails(id: Int)

    //delete all user details
    @Delete
    suspend fun deleteAllUsersDetails(user: User)

}