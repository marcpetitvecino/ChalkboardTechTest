package com.marcpetit.chalkboardtechtest.domain.birthday

import java.time.LocalDate

data class BirthdayDomainModel (
    val title: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate?,
    val age: Int
        )