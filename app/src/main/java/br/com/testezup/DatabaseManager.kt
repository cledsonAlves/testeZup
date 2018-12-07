package br.com.testezup

import android.arch.persistence.room.Room
import br.com.testezup.domain.dao.FilmeDAO
import br.com.testezup.domain.dao.FilmeDatabase


object DatabaseManager {


    // Singleton do Room : banco de Dados
    private var dbInstance: FilmeDatabase

    init {
        val appContext = FilmesApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            FilmeDatabase::class.java,
            "filmes.sqlite"
        )
            .build()

    }

    fun getFilmeDAO(): FilmeDAO {
        return dbInstance.filmeDao()
    }

}