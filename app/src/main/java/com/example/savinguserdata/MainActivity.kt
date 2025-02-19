package com.example.savinguserdata

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val signBtn= findViewById<Button>(R.id.button)
        val name= findViewById<TextInputEditText>(R.id.etName)
        val pNumber= findViewById<TextInputEditText>(R.id.etPhone)
        val email= findViewById<TextInputEditText>(R.id.etEmail)
        val password= findViewById<TextInputEditText>(R.id.etPassword)

        signBtn.setOnClickListener {
            val uName=name.text.toString()
            val m=pNumber.text.toString()
            val emailId=email.text.toString()
            val key=password.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Users")
            val user =User(uName,m,emailId,key)

            database.child(m).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
                name.text?.clear()
                pNumber.text?.clear()
                email.text?.clear()
                password.text?.clear()


            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                name.text?.clear()
                pNumber.text?.clear()
                email.text?.clear()
                password.text?.clear()


            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}