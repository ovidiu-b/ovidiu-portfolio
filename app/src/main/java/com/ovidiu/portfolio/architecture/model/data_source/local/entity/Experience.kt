package com.ovidiu.portfolio.architecture.model.data_source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Professional::class,
        parentColumns = ["id"],
        childColumns = ["professionalId"],
        onDelete = CASCADE
    )]
)
data class Experience(
    @PrimaryKey
    val id: String,
    val company: String,
    val job: String,
    val description: String,
    val dateBegin: Long,
    val dateEnd: Long?,
    val professionalId: String
)