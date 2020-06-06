package com.ovidiu.portfolio.support.test

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.*
import com.ovidiu.portfolio.architecture.model.repository.ProfessionalRepository
import com.ovidiu.portfolio.architecture.model.repository.Result

class FakeProfessionalRepositoryData : ProfessionalRepository {

    private val professionalList: List<Professional> = listOf(
        Professional(
            "idProfessional",
            "Ovidiu",
            "Balaban",
            23,
            "Soy un programador Android",
            "Programador Android"
        )
    )

    private val professionalImage: List<Image> = listOf(
        Image("idImage", "http://someuri.com/idProfessional", "idProfessional")
    )

    private val professionalContactList: LinkedHashMap<String, List<Contact>> = linkedMapOf(
        "idProfessional" to listOf(
            Contact("idContactPhone", "Phone", "631178319", "idProfessional"),
            Contact("idContactEmail", "Email", "b.ovidiu.2312@gmail.com", "idProfessional"),
            Contact("idContactGithub", "Github", "https://github.com/ovidiu-b", "idProfessional"),
            Contact(
                "idContactLinkedin",
                "Linkedin",
                "https://www.linkedin.com/in/ovidiu-balaban-a83731177/",
                "idProfessional"
            )
        )
    )

    private val professionalExperienceList: LinkedHashMap<String, List<Experience>> = linkedMapOf(
        "idProfessional" to listOf(
            Experience(
                "idExperienceOvertel",
                "Overtel Technology Systems",
                "Programador full stack",
                "Descripción experience",
                1520636400000,
                null,
                "idProfessional"
            )
        )
    )

    private val professionalStudyList: LinkedHashMap<String, List<Study>> = linkedMapOf(
        "idProfessional" to listOf(
            Study(
                "idStudyDam",
                "I.E.S. Carlos III",
                "Desarrollo de aplicaciones multiplataforma",
                "Grado superior de desarrollo de aplicaciones multiplataforma",
                1525816800000,
                1528236000000,
                "idProfessional"
            )
        )
    )

    override suspend
    fun getProfessionalByNameAndSurname(
        name: String,
        surname: String
    ): Result<Professional?> {

        return Result.Success(professionalList.first { it.name == name && it.surname == surname })
    }

    override suspend
    fun getProfessionalImage(idProfessional: String): Image {
        return professionalImage.first { it.professionalId == idProfessional }
    }

    override suspend
    fun getProfessionalContactList(idProfessional: String): List<Contact> {
        return professionalContactList[idProfessional]!!
    }

    override suspend
    fun getProfessionalExperienceList(idProfessional: String): List<Experience> {
        return professionalExperienceList[idProfessional]!!
    }

    override suspend
    fun getProfessionalStudyList(idProfessional: String): List<Study> {
        return professionalStudyList[idProfessional]!!
    }
}