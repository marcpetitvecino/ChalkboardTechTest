package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.marcpetit.chalkboardtechtest.data.birthday.BirthdayDTO
import com.marcpetit.chalkboardtechtest.data.birthday.BirthdayMapper
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.data.repository.Mapper

class ResultsMapper(private val birthdayMapper: Mapper<BirthdayDomainModel, BirthdayDTO>)
    : Mapper<List<BirthdayDomainModel>, ResultsDTO> {

    companion object {
        const val NAME = "ResultsMapper"
    }

    override fun mapToDomainModel(dto: ResultsDTO): List<BirthdayDomainModel> {
        return dto.results.map { birthdayMapper.mapToDomainModel(it) }
    }
}