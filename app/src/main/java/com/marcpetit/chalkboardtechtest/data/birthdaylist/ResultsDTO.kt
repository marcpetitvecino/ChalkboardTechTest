package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.google.gson.annotations.SerializedName

data class ResultsDTO (
    @SerializedName("results") val results: List<BirthdayDTO>
        )