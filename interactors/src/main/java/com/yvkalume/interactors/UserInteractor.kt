package com.yvkalume.interactors

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserInteractor(private val firestore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) {
    private val currentUser by lazy {
        firebaseAuth.currentUser
    }

    suspend fun userIsAuth() = flow {
        val isAuth = currentUser != null
        emit(isAuth)
    }

    suspend fun signIn(account: GoogleSignInAccount) = callbackFlow {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)

        firebaseAuth.signInWithCredential(credential).await()
        val user = User(account.id.toString(),account.displayName, account.photoUrl.toString())
        saveUser(user)
            .addOnCompleteListener {
                if (!isClosedForSend) offer(true)
            }
            .addOnFailureListener {
                firebaseAuth.currentUser?.delete()
                if (!isClosedForSend) offer(false)
            }
        awaitClose()
    }

    private fun saveUser(user: User): Task<Void> {
        return firestore.collection(FirebasePath.USER)
            .document(user.uid)
            .set(user)
    }
}