package com.ovidiu.portfolio.architecture.model.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact where professionalId=:professionalId")
    fun getAllByProfessionalId(professionalId: String): List<Contact>

    @Insert
    fun insert(contact: Contact)
}