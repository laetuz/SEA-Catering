package id.neotica.seacatering

import android.app.Application
import co.touchlab.kermit.Logger
import com.google.firebase.FirebaseApp
import com.google.firebase.Firebase
import com.google.firebase.initialize
import id.neotica.seacatering.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this@MyApp)
        val apps = FirebaseApp.getApps(this)
        Logger.d("FirebaseCheck") {
            "Apps initialized: ${apps.map { it.name }}"
        }
        initializeKoin { androidContext(this@MyApp) }
    }
}