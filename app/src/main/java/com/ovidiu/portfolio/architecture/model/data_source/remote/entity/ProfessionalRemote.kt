package com.ovidiu.portfolio.architecture.model.data_source.remote.entity

import androidx.annotation.Keep

@Keep
class ProfessionalRemote {
    var id = ""
    var name = ""
    var surname = ""
    var age: Int = 0
    var description = ""
    var title = ""
}