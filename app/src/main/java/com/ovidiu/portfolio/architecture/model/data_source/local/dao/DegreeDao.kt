package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Degree

@Dao
interface DegreeDao {
    @Insert
    fun insert(vararg studies: Degree)
}