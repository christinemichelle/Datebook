package com.example.datebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datebook.databinding.ActivityDodoBinding
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat

class DodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDodoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.savebtn.setOnClickListener {
            UploadData()
        }

    }
    private fun UploadData(){
        val title = binding.title.text.toString()
        val description = binding.description.toString()
        val priority = binding.priority.toString()

        val dataclass =Dataclass(title,description,priority)
        FirebaseDatabase.getInstance().getReference("To do list")
            .setValue(dataclass).addOnCompleteListener { task->
                if (task.isSuccessful){
                    Toast.makeText(this@DodoActivity, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener{e->
                Toast.makeText(this@DodoActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

}