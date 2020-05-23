package com.ovidiu.portfolio.architecture.model.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ovidiu.portfolio.architecture.model.data_source.local.dao.*
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*

const val DATABASE_NAME = "LocalDB"

@Database(
    entities = [Professional::class, SocialMediaProfile::class, Image::class, Experience::class, Degree::class],
    version = 1
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun professionalDao(): ProfessionalDao
    abstract fun socialMediaProfileDao(): SocialMediaProfileDao
    abstract fun imageDao(): ImageDao
    abstract fun experienceDao(): ExperienceDao
    abstract fun degreeDao(): DegreeDao
}