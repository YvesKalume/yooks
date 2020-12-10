package com.yvkalume.interactors

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.interactors.util.FirebasePath
import com.yvkalume.model.domain.User

class UserInteractor(private val firestore: FirebaseFirestore) {
    fun addUser(user: User) {
        val query = firestore.collection("/${FirebasePath.USER}")
        query.add(user)
    }
}