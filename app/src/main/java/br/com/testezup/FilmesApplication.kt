package br.com.testezup

import android.util.Log
import android.support.multidex.MultiDexApplication
import java.lang.IllegalStateException

class FilmesApplication : MultiDexApplication() {
private val TAG = "CarrosApplication"


// Chamado quando o Android criar o processo da aplicação
override fun onCreate() {
    super.onCreate()
    // Salva a instância para termos acesso como Singleton
    appInstance = this
}

companion object {
    // Singleton da classe Application
    private var appInstance: FilmesApplication? = null

    fun getInstance(): FilmesApplication {
        if (appInstance == null) {
            throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
        }
        return appInstance!!
    }
}

// Chamado quando o Android finalizar o processo da aplicação
override fun onTerminate() {
    super.onTerminate()
    Log.d(TAG, "FilmesApplication.onTerminate()")
}
}