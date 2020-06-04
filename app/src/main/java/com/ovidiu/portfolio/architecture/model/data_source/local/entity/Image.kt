package com.ovidiu.portfolio.architecture.model.data_source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ovidiu.portfolio.architecture.model.data_source.remote.entity.ImageRemote

@Entity(
    foreignKeys = [ForeignKey(
        entity = Professional::class,
        parentColumns = ["id"],
        childColumns = ["professionalId"],
        onDelete = CASCADE
    )]
)
data class Image(
    @PrimaryKey
    val id: String,
    val url: String,
    val professionalId: String
) {
    @Ignore
    constructor(imageRemote: ImageRemote) : this(
        imageRemote.id,
        imageRemote.url,
        imageRemote.professionalId
    )
}