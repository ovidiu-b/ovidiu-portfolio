package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Image

@Dao
interface ImageDao {
    @Query("SELECT url FROM Image WHERE professionalId=:professionalId")
    fun getUrlByProfessionalId(professionalId: String): String

    @Insert
    fun insert(image: Image)
}