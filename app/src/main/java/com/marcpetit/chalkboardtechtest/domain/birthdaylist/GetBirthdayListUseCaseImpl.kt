package com.marcpetit.chalkboardtechtest.domain.birthdaylist

import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.data.repository.DataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBirthdayListUseCaseImpl(
    private val birthdayListDataSource: BirthdayListDataSource
    ): GetBirthdayListUseCase {

    override fun invoke(p1: Unit): Flow<List<BirthdayDomainModel>> = flow {
        when(val response= birthdayListDataSource.getBirthdayList()) {
            is DataResponse.Failure -> throw response.error
            is DataResponse.Success -> {
                emit(response.data)
            }
        }
    }
}