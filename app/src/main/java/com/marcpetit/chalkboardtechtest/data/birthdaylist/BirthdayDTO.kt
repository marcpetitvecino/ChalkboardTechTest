package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.google.gson.annotations.SerializedName

data class BirthdayDTO (
    @SerializedName("name") val name: NameDTO,
    @SerializedName("dob") val dateOfBirth: DobDTO
        )