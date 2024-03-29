package com.ovidiu.portfolio.architecture.model.data_source.remote.entity

import androidx.annotation.Keep

@Keep
class StudyRemote {
    var id = ""
    var dateBegin: Long = 0
    var dateEnd: Long? = null
    var description = ""
    var school = ""
    var title = ""
    var professionalId = ""
}