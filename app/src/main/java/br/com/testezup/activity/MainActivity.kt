package br.com.testezup.activity

import android.os.Bundle
import android.util.Log
import br.com.testezup.R
import br.com.testezup.domain.FilmesServiceOkhttp
import br.com.testezup.model.FilmeModel
import org.jetbrains.anko.uiThread


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskFilmes()

    }

}

private fun taskFilmes() {
    object : Thread() {
        override fun run() {
            var filmeModel = FilmesServiceOkhttp.getFilme()
            Log.i("#LISTA ....", "${filmeModel.toString()}")


        }
    }.start()


}
