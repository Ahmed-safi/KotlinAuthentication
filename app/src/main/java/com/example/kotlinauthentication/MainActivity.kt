package com.example.kotlinauthentication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        SignIn.setOnClickListener{
            signInUser(email.text.toString(),pass.text.toString())
        }
        SignUp.setOnClickListener{
            startActivity(Intent(applicationContext, SinUpActivity::class.java))
        }
    }
    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user=auth.currentUser
                    Toast.makeText(
                        applicationContext,
                        "${user?.email} >> Login is done",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}