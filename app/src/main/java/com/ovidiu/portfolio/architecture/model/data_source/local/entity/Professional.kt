package com.ovidiu.portfolio.architecture.model.data_source.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ovidiu.portfolio.architecture.model.data_source.remote.entity.ProfessionalRemote

@Entity
data class Professional(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
    val age: Int,
    val description: String,
    val title: String
) {
    @Ignore
    constructor(professionalRemote: ProfessionalRemote) : this(
        professionalRemote.id,
        professionalRemote.name,
        professionalRemote.surname,
        professionalRemote.age,
        professionalRemote.description,
        professionalRemote.title
    )
}