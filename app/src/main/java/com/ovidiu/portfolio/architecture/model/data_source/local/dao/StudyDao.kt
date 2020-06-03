package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study

@Dao
interface StudyDao {
    @Insert
    fun insert(vararg studies: Study)

    @Query("SELECT * FROM Study WHERE professionalId=:professionalId")
    fun getAllByProfessionalId(professionalId: String): List<Study>
}