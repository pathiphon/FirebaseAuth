package com.adedom.firebaseauth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (firebaseAuth.currentUser == null) {
            Intent(baseContext, SignInActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(this)
            }
            return
        }

        bt_update_phone.setOnClickListener {
            Intent(baseContext, UpdatePhoneActivity::class.java).apply {
                startActivity(this)
            }
        }

        bt_sign_out.setOnClickListener {
            firebaseAuth.signOut()
            Intent(baseContext, SignInActivity::class.java).apply {
                startActivity(this)
            }
        }

    }

}
