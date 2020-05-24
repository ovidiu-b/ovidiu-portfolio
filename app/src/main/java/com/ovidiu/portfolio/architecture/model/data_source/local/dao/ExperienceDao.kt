package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience

@Dao
interface ExperienceDao {
    @Insert
    fun insert(vararg experiences: Experience)
}