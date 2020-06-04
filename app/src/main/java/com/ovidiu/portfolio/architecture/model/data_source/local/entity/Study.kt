package com.ovidiu.portfolio.architecture.model.data_source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.ovidiu.portfolio.architecture.model.data_source.remote.entity.StudyRemote

@Entity(
    foreignKeys = [ForeignKey(
        entity = Professional::class,
        parentColumns = ["id"],
        childColumns = ["professionalId"],
        onDelete = CASCADE
    )]
)
data class Study(
    @PrimaryKey
    val id: String,
    val school: String,
    val title: String,
    val description: String,
    val dateBegin: Long,
    val dateEnd: Long?,
    val professionalId: String
) {
    @Ignore
    constructor(studyRemote: StudyRemote) : this(
        studyRemote.id,
        studyRemote.school,
        studyRemote.title,
        studyRemote.description,
        studyRemote.dateBegin,
        studyRemote.dateEnd,
        studyRemote.professionalId
    )
}