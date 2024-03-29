package com.ovidiu.portfolio.architecture.model.data_source.remote.entity

import androidx.annotation.Keep

@Keep
class ExperienceRemote {
    var id = ""
    var company = ""
    var dateBegin: Long = 0
    var dateEnd: Long? = null
    var description = ""
    var job = ""
    var professionalId = ""
}