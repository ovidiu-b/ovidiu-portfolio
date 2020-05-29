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
data class Contact(
    @PrimaryKey
    val id: String,
    val contactType: String,
    val value: String,
    val professionalId: String
)

enum class ContactType(val type: String) {
    PHONE("Phone"),
    EMAIL("Email"),
    GITHUB("Github"),
    LINKEDIN("Linkedin")
}