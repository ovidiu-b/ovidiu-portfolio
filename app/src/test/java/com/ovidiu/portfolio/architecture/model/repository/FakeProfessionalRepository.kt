package com.ovidiu.portfolio.architecture.model.repository

import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional

class FakeProfessionalRepository : ProfessionalRepository {
    private val professionalList: List<Professional> = List(1) {
        Professional(
            "1",
            "Ovidiu",
            "Balaban",
            23,
            "Soy un programador Android",
            "Programador Android",
            "631178319",
            "b.ovidiu.2312@gmail.com"
        )
    }

    override suspend fun getProfessionalByNameAndSurname(
        name: String,
        surname: String
    ): Professional {

        return professionalList.first { it.name == name && it.surname == surname }
    }
}