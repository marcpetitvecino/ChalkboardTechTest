package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.google.gson.annotations.SerializedName

data class NameDTO (
    @SerializedName("title") val title: String,
    @SerializedName("first") val firstName: String,
    @SerializedName("last") val lastName: String,
        )