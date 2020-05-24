package com.ovidiu.portfolio.architecture.model.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Professional(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
    val age: Int,
    val description: String,
    val title: String,
    val phone: String,
    val email: String
)