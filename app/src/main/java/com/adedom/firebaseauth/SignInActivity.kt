package com.adedom.firebaseauth

import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.PhoneAuthCredential

class SignInActivity : BaseVerifyOtp() {

    override fun firebaseAuthPhone(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Intent(baseContext, MainActivity::class.java).apply {
                        finishAffinity()
                        startActivity(this)
                    }
                } else {
                    task.exception?.message?.let { toast(it, Toast.LENGTH_LONG) }
                }
            }
    }

}
