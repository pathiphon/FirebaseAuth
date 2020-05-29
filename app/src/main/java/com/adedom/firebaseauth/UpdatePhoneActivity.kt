package com.adedom.firebaseauth

import android.widget.Toast
import com.google.firebase.auth.PhoneAuthCredential

class UpdatePhoneActivity : BaseVerifyOtp() {

    override fun firebaseAuthPhone(credential: PhoneAuthCredential) {
        firebaseAuth.currentUser?.updatePhoneNumber(credential)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                finish()
            } else {
                task.exception?.message?.let { toast(it, Toast.LENGTH_LONG) }
            }
        }
    }

}
