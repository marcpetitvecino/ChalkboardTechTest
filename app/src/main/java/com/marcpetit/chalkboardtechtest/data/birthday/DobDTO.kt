package com.marcpetit.chalkboardtechtest.data.birthday

import com.google.gson.annotations.SerializedName

data class DobDTO (
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
        )