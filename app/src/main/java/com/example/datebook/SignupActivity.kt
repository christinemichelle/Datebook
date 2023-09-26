package com.example.datebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datebook.databinding.ActivitySignupBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SignupActivity : AppCompatActivity() {

    private lateinit var binding :ActivitySignupBinding
    private lateinit var firebasedatabase: FirebaseDatabase
    private lateinit var databasereference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebasedatabase =FirebaseDatabase.getInstance()
        databasereference = firebasedatabase.reference.child("users")

        binding.SignUp.setOnClickListener {
            val signupUsername = binding.Username.text.toString()
            val signupPassword = binding.Password.text.toString()

            if (signupUsername.isNotEmpty() && signupPassword.isNotEmpty()){
                signupUser(signupUsername, signupPassword)
            } else{
                Toast.makeText(this@SignupActivity, "All fields are required", Toast.LENGTH_SHORT ).show()

            }

        }
        binding.LoginRedirect.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }

    }
    private fun signupUser(username: String, password: String ){
        databasereference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (!datasnapshot.exists()){
                    val id = databasereference.push().key
                    val userdata = Userdata(id, username, password)
                    databasereference.child(id!!).setValue(userdata)
                    Toast.makeText(this@SignupActivity, "Signup Suuccessful", Toast.LENGTH_SHORT ).show()
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(this@SignupActivity,"User already exists", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignupActivity, "Error!: ${databaseError.message}", Toast.LENGTH_SHORT ).show()

            }
        })
    }

}