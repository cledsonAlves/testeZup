package br.com.testezup.domain.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.testezup.domain.dao.FilmeDAO
import br.com.testezup.model.FilmeModel

// Define as classes que precisam ser persistidas e a versão do banco
@Database(entities = arrayOf(FilmeModel::class), version = 1)
abstract class FilmeDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmeDAO
}