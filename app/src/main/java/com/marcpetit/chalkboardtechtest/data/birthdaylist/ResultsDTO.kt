package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.google.gson.annotations.SerializedName
import com.marcpetit.chalkboardtechtest.data.birthday.BirthdayDTO

data class ResultsDTO (
    @SerializedName("results") val results: List<BirthdayDTO>
        )