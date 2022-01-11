package com.marcpetit.chalkboardtechtest.domain.birthday

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class BirthdayDomainModel (
    val title: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate?,
    val age: Int
        ): Parcelable