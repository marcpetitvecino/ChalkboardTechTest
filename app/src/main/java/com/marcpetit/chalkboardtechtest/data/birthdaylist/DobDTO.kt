package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.google.gson.annotations.SerializedName

data class DobDTO (
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
        )