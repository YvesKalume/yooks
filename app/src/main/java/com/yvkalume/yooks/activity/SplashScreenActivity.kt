package com.yvkalume.yooks.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.yooks.MainActivity
import com.yvkalume.yooks.R
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.User
import com.yvkalume.yooks.util.runAnimationUp
import kotlinx.android.synthetic.main.activity_splash_screen.*
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
                googleSignin.runAnimationUp()
                googleSignin.isVisible = true
            }
            else {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
        }

        setUpListener()
    }

    private fun setUpListener() {
        googleSignin.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        startActivityForResult(googleSignInClient.signInIntent, RC_GOOGLE_SIGN_IN)
    }

    companion object RequestCode {
        const val RC_GOOGLE_SIGN_IN = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                signIn(account)

                Log.d("SplashScreenActivity", "firebaseAuthWithGoogle:" + account.id)
            } catch (e: ApiException) {
                // Google Sign In failed
                // TODO :  update UI appropriately
                Log.w("SplashScreenActivity", "Google sign in failed", e)

            }
        }
    }


    private fun signIn(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = User(account.id.toString(),account.displayName, account.photoUrl.toString())
                saveUser(user)
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
                onRestart()
            }
    }
}