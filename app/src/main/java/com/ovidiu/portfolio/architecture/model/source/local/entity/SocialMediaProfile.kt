package com.ovidiu.portfolio.architecture.model.source.local.entity

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
data class SocialMediaProfile(
    @PrimaryKey
    val id: String,
    val socialMediaName: String,
    val profileUrl: String,
    val professionalId: String
)