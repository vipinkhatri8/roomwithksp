package com.yesitlab.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<List<UserData>>()
    val users: LiveData<List<UserData>> get() = _users

    fun insertUser(user: UserData) = viewModelScope.launch {
        repository.insertUser(user)
        loadUsers() // Load users after insertion
    }

    fun loadUsers() = viewModelScope.launch {
        _users.postValue(repository.getAllUsers())
    }

    fun deleteUser(user: UserData) = viewModelScope.launch {
        repository.deleteUser(user)
        loadUsers() // Load users after deletion
    }
    // Add update user function
    fun updateUser(user: UserData) = viewModelScope.launch {
        repository.updateUser(user)
        loadUsers() // Reload users after updating
    }

}
