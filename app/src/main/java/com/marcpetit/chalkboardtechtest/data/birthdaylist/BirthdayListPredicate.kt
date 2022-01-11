package com.marcpetit.chalkboardtechtest.data.birthdaylist

import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.data.repository.Mapper
import com.marcpetit.data.repository.RetrofitPredicate

class BirthdayListPredicate(
    private val mapper: Mapper<List<BirthdayDomainModel>, ResultsDTO>):
    RetrofitPredicate<BirthdayListAPI, List<BirthdayDomainModel>, ResultsDTO> {

    override fun mapper(): Mapper<List<BirthdayDomainModel>, ResultsDTO> = mapper

    override fun service(): Class<BirthdayListAPI> = BirthdayListAPI::class.java

    override fun endpoint(): suspend (BirthdayListAPI) -> ResultsDTO = {it.getBirthDayList()}

}