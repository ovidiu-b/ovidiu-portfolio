package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience

@Dao
interface ExperienceDao {
    @Insert
    fun insert(vararg experiences: Experience)

    @Query("SELECT * FROM Experience WHERE professionalId=:professionalId")
    fun getAllByProfessionalId(professionalId: String): List<Experience>
}