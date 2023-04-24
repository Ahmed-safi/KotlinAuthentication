package com.example.kotlinauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.singup_activity.*
import kotlinx.android.synthetic.main.singup_activity.view.*
import kotlin.math.sign

class SinUpActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.singup_activity)

        auth=Firebase.auth
        SignIn2.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))

        }
        SignUp2.setOnClickListener {
           createUser(email2.text.toString(),pass2.text.toString())
        }


    }
    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user=auth.currentUser
                    Toast.makeText(
                        applicationContext,
                        "${user?.email} >> the registration is done",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Registration failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}