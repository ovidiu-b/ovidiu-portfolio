package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

@Dao
interface ProfessionalDao {
    @Insert
    fun insert(professional: Professional)

    @Query("SELECT * FROM Professional WHERE name=:name and surname=:surname")
    suspend fun getByNameAndSurname(name: String, surname: String): Professional
}