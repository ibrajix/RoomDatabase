package com.ibrajix.roomdatabase.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ibrajix.roomdatabase.model.User
import com.ibrajix.roomdatabase.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel @ViewModelInject constructor(private val userRepo: UserRepo): ViewModel() {


    /**
     * Insert user details
     */
    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long> = _response

    //insert user details to room database
    fun insertUserDetails(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(userRepo.createUserRecords(user))
        }
    }

    /**
     * Retrieve user details
     */
    //check if song is liked
    private val _userDetails = MutableStateFlow<List<User>>(emptyList())
    val userDetails : StateFlow<List<User>> =  _userDetails

    fun doGetUserDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.getUserDetails
                .catch { e->
                    //Log error here
                }
                .collect {
                    _userDetails.value = it
                }
        }
    }

    /**
     * Delete single user record
     */
    fun doDeleteSingleUserRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteSingleUserRecord()
        }
    }

}