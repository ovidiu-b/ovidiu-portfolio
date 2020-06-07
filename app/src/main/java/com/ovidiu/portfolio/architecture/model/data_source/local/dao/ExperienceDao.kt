package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience

@Dao
interface ExperienceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(experienceList: List<Experience>)

    @Query("SELECT * FROM Experience WHERE professionalId=:professionalId ORDER BY dateBegin DESC")
    fun getAllByProfessionalId(professionalId: String): List<Experience>
}