package com.line.fukuokabserver.Auth

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.stereotype.Component
import java.io.FileInputStream

@Component
class FireAuth: Auth {
    companion object {
        init {
            val FIREBASE_SERVICE_ACCOUNT = "./fukuoka-b-firebase-adminsdk-64w7f-21a104999e.json"
            val stream = FileInputStream(FIREBASE_SERVICE_ACCOUNT)
            val ACCOUNT = FireAuth::class.java.getResourceAsStream(FIREBASE_SERVICE_ACCOUNT)
            val OPTIONS = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .setDatabaseUrl("https://fukuoka-b.firebaseio.com")
                    .build()

            FirebaseApp.initializeApp(OPTIONS)
        }
    }

    override fun verifyIdToken(id_token: String?): String? {
        try {
            val decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(id_token).get()
            val uid = decodedToken.uid;
            return uid
        }
        catch (e: Exception) {
            return null
        }
    }
}