package com.yvkalume.dcplus.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.dcplus.MainActivity
import com.yvkalume.dcplus.R
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashScreenActivity : AppCompatActivity() {

    private val firebaseAuth by inject<FirebaseAuth>()
    private val firestore by inject<FirebaseFirestore>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(3000)
            if (firebaseAuth.currentUser?.uid == null) {
                launchSigninFlow()
            }
            else {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
        }
    }

    private fun launchSigninFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher_round)
                .setTheme(R.style.AppTheme)
                .build(),
            RC_SIGN_IN)
    }

    companion object RequestCode {
        const val RC_SIGN_IN = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val userInfo = firebaseAuth.currentUser
                val user = User(userInfo!!.uid,userInfo.displayName,userInfo.photoUrl.toString())
                saveUser(user)
                // ...
            } else {
                if (response != null) {
                    Log.d("MainActivity","${response.error}")
                }
            }
        }
    }

    private fun saveUser(user: User) {
        firestore.collection(FirebasePath.USER)
            .document(user.uid)
            .set(user)
            .addOnCompleteListener {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                firebaseAuth.currentUser?.delete()
                launchSigninFlow()
            }
    }
}