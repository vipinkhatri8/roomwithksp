package com.yesitlab.room

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: UserData) {
        userDao.insertUser(user)
    }

    suspend fun getAllUsers(): List<UserData> {
        return userDao.getAllUsers()
    }

    suspend fun deleteUser(user: UserData) {
        userDao.deleteUser(user)
    }
    // Add update method
    suspend fun updateUser(user: UserData) {
        userDao.updateUser(user)
    }
}
