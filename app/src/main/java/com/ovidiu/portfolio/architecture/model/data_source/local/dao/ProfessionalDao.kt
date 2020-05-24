package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

@Dao
interface ProfessionalDao {
    @Insert
    fun insert(professional: Professional)
}