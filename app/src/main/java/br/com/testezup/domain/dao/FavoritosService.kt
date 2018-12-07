package br.com.testezup.domain.dao

import android.util.Log
import br.com.testezup.DatabaseManager
import br.com.testezup.model.FilmeModel


object FavoritosService{
    // retorn todos os filmes
    fun getFilmes() : List<FilmeModel>{
        val dao = DatabaseManager.getFilmeDAO()
        val filmes = dao.findAll()
        return filmes

    }
    //  salva ou deleta o filme
    fun update(filme : FilmeModel): Boolean{
       val dao = DatabaseManager.getFilmeDAO()
        dao.insert(filme)
        Log.i("#INSERT","NOVO FILME INSERIDO : ${filme.title}")
        return true
    }
}