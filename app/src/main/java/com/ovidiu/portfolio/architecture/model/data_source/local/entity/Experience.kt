package com.ovidiu.portfolio.architecture.model.data_source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ovidiu.portfolio.architecture.model.data_source.remote.entity.ExperienceRemote

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
) {
    @Ignore
    constructor(experienceRemote: ExperienceRemote) : this(
        experienceRemote.id,
        experienceRemote.company,
        experienceRemote.job,
        experienceRemote.description,
        experienceRemote.dateBegin,
        experienceRemote.dateEnd,
        experienceRemote.professionalId
    )
}