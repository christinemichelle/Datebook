package com.example.datebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.datebook.databinding.ActivityTaskBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.EventListener
import com.google.firebase.database.ChildEventListener

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var datalist : ArrayList<Dataclass>
    private lateinit var adapter: MyAdapter
    var databaseReference: DatabaseReference? = null
    var eventListener:EventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gridLayoutManager = GridLayoutManager(this@TaskActivity,1)
        binding.recyclerview.layoutManager=gridLayoutManager





        datalist= ArrayList()
        adapter= MyAdapter(this@TaskActivity, datalist)
        binding.recyclerview.adapter= adapter
        databaseReference= FirebaseDatabase.getInstance().getReference("To do list")



        eventListener = object :ValueEventListener, EventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(Dataclass::class.java)
                    if (dataClass != null) {
                        datalist.add(dataClass)
                    }
                }
                adapter.notifyDataSetChanged()
                finish()
            }
            override fun onCancelled(error: DatabaseError) {
                finish()
            }
        }



        binding.fab.setOnClickListener {
            val intent= Intent(this, DodoActivity::class.java)
            startActivity(intent)
        }
    }
}