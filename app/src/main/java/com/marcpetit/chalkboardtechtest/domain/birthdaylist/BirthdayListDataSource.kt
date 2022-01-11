package com.marcpetit.chalkboardtechtest.domain.birthdaylist

import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.data.repository.DataResponse

interface BirthdayListDataSource {
    suspend fun getBirthdayList(): DataResponse<List<BirthdayDomainModel>>
}