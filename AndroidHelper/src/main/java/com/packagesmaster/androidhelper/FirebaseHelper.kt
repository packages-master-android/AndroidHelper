package com.packagesmaster.androidhelper

//import com.google.firebase.messaging.FirebaseMessaging
//import com.example.finalproject.eduhub.model.PushNotification
//import com.example.finalproject.eduhub.model.RetrofitInstance

class FirebaseHelper {

//    companion object {
//        private lateinit var instance: FirebaseHelper
//
//        fun getFirebaseHelper(): FirebaseHelper {
//            return instance
//        }
//    }
//
//    init {
//        instance = this
//    }
//
//    fun sendNotification(notification: PushNotification) = CoroutineScope(
//        Dispatchers.IO).launch {
//        try {
//            val response = RetrofitInstance.api.postNotification(notification)
//            if (response.isSuccessful) {
//                Log.e("msg_notify_success", "success")
//            }
//        }
//        catch(e: Exception) {}
//    }
//
//    /**
//     * This function is just for registering the firebase messaging for a single user
//     * You can use it to send a private notification to all user devices
//     */
//
//    fun registerFirebaseMessaging() {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener {
//            if (it.isSuccessful) {
//                Log.e("msg_success", "success, token = $it")
//            }
//            else {
//                Log.e("msg_failed", "failed <--> ${it.exception!!.localizedMessage}")
//            }
//        }
//    }
//
//    fun registerTopic(topic: String) {
//        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$topic")
//    }
//
//    fun unregisterTopic(topic: String) {
//        FirebaseMessaging.getInstance().unsubscribeFromTopic("/topics/$topic")
//    }

}