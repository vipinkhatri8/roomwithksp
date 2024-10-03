package com.yesitlab.room

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yesitlab.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var itemAdapter: ItemAdapter
    private var userIdToDelete: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // Initialize ViewModel
        val database = UserDatabase.getDatabase(applicationContext)
        val userDao = database.userDao()
        val repository = UserRepository(userDao)
        userViewModel = UserViewModel(repository)

        itemAdapter = ItemAdapter(this, mutableListOf(), this)
        binding.recyclerView.adapter = itemAdapter // Assuming you have a RecyclerView in your layout

        // Observe the users LiveData
        userViewModel.users.observe(this, Observer { users ->
            itemAdapter.updateData(users)
        })

        // Add User button click listener
        binding.buttonAddUser.setOnClickListener {
            val name = binding.editTextUserName.text.toString()
            val age = binding.editTextUserAge.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                val user = UserData(name = name, age = age)
                userViewModel.insertUser(user)
                binding.editTextUserName.text.clear()
                binding.editTextUserAge.text.clear()
            }
        }
    }

    override fun clickEvent(position: Int,name : String) {
        when(name){
            "Delete"->{
                val userToDelete = itemAdapter.list[position]
                userViewModel.deleteUser(userToDelete)
            }
            "Edit"->{
                alertBox(position)
            }
        }


    }

    private fun alertBox(position: Int) {
        val postDialog = Dialog(this)
        postDialog.setContentView(R.layout.edit_dialog)
        postDialog.setCancelable(true)

        val userToEdit = itemAdapter.list[position]

        val etName: EditText = postDialog.findViewById(R.id.etName)
        val etAge: EditText = postDialog.findViewById(R.id.etAge)
        val submit: Button = postDialog.findViewById(R.id.submit)

        // Set existing data
        etName.setText(userToEdit.name)
        etAge.setText(userToEdit.age)

        // Update user on submit
        submit.setOnClickListener {
            val updatedName = etName.text.toString()
            val updatedAge = etAge.text.toString()

            if (updatedName.isNotEmpty() && updatedAge.isNotEmpty()) {
                val updatedUser = userToEdit.copy(name = updatedName, age = updatedAge)
                userViewModel.updateUser(updatedUser)
            }

            postDialog.dismiss()
        }

        postDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        postDialog.show()
    }
}
