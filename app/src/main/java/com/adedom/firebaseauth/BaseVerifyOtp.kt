package com.adedom.firebaseauth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verify_otp.*
import java.util.concurrent.TimeUnit

abstract class BaseVerifyOtp : AppCompatActivity(){

    protected var firebaseAuth = FirebaseAuth.getInstance()
    protected var verificationId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        bt_request_otp.setOnClickListener {
            requestOtp()
        }

        bt_verify_phone.setOnClickListener {
            if (verificationId != null) {
                val smsCode = et_verify_phone.getContents()
                val credential = PhoneAuthProvider.getCredential(verificationId!!, smsCode)
                firebaseAuthPhone(credential)
            }
        }
    }

    private fun requestOtp() {
        val phoneNumber = et_phone.getContents().phoneNumberThai()

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                et_verify_phone.setText(credential.smsCode)
                firebaseAuthPhone(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                e.message?.let { toast(it, Toast.LENGTH_LONG) }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                this@BaseVerifyOtp.verificationId = verificationId
            }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            callbacks
        )
    }

    abstract fun firebaseAuthPhone(credential: PhoneAuthCredential)

}
