package br.com.testezup.domain.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.testezup.model.FilmeModel

@Dao
interface FilmeDAO{
    @Query("SELECT  * FROM filme where imdbID = :id")
    fun getByID(id: Long): FilmeModel?

    @Query("SELECT * FROM filme")
     fun findAll(): List<FilmeModel>

    @Insert
     fun insert(filmeModel: FilmeModel)

    @Delete
    fun detele(filmeModel: FilmeModel)
}