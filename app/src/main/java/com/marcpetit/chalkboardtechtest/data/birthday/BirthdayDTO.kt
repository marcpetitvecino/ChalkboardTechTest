package com.marcpetit.chalkboardtechtest.data.birthday

import com.google.gson.annotations.SerializedName

data class BirthdayDTO (
    @SerializedName("name") val name: NameDTO,
    @SerializedName("dob") val dateOfBirth: DobDTO
        )