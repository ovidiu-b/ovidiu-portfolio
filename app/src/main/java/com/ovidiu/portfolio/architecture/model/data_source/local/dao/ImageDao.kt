package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM Image WHERE professionalId=:professionalId")
    fun getImageByProfessionalId(professionalId: String): Image

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: Image)
}