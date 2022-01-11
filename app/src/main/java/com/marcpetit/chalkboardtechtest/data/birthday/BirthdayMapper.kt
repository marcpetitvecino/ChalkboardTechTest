package com.marcpetit.chalkboardtechtest.data.birthday

import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.data.repository.Mapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BirthdayMapper: Mapper<BirthdayDomainModel, BirthdayDTO> {

    companion object {
        const val NAME = "BirthdayMapper"
    }

    override fun mapToDomainModel(dto: BirthdayDTO): BirthdayDomainModel {
        return with(dto) {
            BirthdayDomainModel(
                title = name.title,
                firstName = name.firstName,
                lastName = name.lastName,
                dateOfBirth = formatDate(dateOfBirth.date),
                age = dateOfBirth.age
            )
        }
    }

    private fun formatDate(dateString: String?): LocalDate? {
        val format = DateTimeFormatter.ofPattern("yyyB-MM-dd'T'HH:mm:ss.SSS'Z'")
        return try {
            LocalDate.parse(dateString, format)
        } catch(e: Exception) {
            null
        }
    }

}