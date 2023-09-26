package com.example.datebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datebook.databinding.ActivityLoginBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase =FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.LoginSignUp.setOnClickListener {
            val LoginUsername = binding.LoginUsername.text.toString()
            val LoginPassword = binding.LoginPassword.text.toString()

            if (LoginUsername.isNotEmpty() && LoginPassword.isNotEmpty()){
                LoginUser(LoginUsername, LoginPassword)
            } else{
                Toast.makeText(this@LoginActivity, "All fields are required", Toast.LENGTH_SHORT ).show()

            }
        }
        binding.SignUpRedirect.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }
    }

    private  fun LoginUser(username:String, password: String){
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot.exists()){
                    for (userSnapshot in datasnapshot.children){
                        val userdata = userSnapshot.getValue(Userdata::class.java)

                        if (userdata!= null && userdata.password == password){
                            Toast.makeText(this@LoginActivity, "Login Successful" , Toast.LENGTH_LONG ).show()
                            startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java))
                            finish()
                            return

                        }
                    }
                }
                Toast.makeText(this@LoginActivity, "Login Failed" , Toast.LENGTH_LONG ).show()

            }

            override fun onCancelled(databaseerror: DatabaseError) {
                Toast.makeText(this@LoginActivity,"Database error !:${databaseerror.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}