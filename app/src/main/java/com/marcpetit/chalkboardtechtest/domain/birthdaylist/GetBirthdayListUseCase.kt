package com.marcpetit.chalkboardtechtest.domain.birthdaylist

import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import kotlinx.coroutines.flow.Flow

interface GetBirthdayListUseCase: (Unit) -> Flow<List<BirthdayDomainModel>>