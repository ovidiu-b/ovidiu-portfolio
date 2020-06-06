package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study

@Dao
interface StudyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(studyList: List<Study>)

    @Query("SELECT * FROM Study WHERE professionalId=:professionalId ORDER BY dateBegin desc")
    fun getAllByProfessionalId(professionalId: String): List<Study>
}