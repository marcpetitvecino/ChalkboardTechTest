package com.marcpetit.chalkboardtechtest.di.birthdaylist

import com.marcpetit.chalkboardtechtest.data.birthday.BirthdayDTO
import com.marcpetit.chalkboardtechtest.data.birthday.BirthdayMapper
import com.marcpetit.chalkboardtechtest.data.birthdaylist.BirthdayListDataSourceImpl
import com.marcpetit.chalkboardtechtest.data.birthdaylist.ResultsDTO
import com.marcpetit.chalkboardtechtest.data.birthdaylist.ResultsMapper
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.chalkboardtechtest.domain.birthdaylist.BirthdayListDataSource
import com.marcpetit.chalkboardtechtest.domain.birthdaylist.GetBirthdayListUseCase
import com.marcpetit.chalkboardtechtest.domain.birthdaylist.GetBirthdayListUseCaseImpl
import com.marcpetit.data.network.NetworkClient
import com.marcpetit.data.repository.Mapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

val birthdayListModule = module {
    single<Mapper<BirthdayDomainModel, BirthdayDTO>>(named(BirthdayMapper.NAME)) {
        BirthdayMapper()
    }

    single<Mapper<List<BirthdayDomainModel>, ResultsDTO>>(named(ResultsMapper.NAME)) {
        ResultsMapper(birthdayMapper = get(named(BirthdayMapper.NAME)))
    }

    single<BirthdayListDataSource> {
        BirthdayListDataSourceImpl(
            client = get(named(NetworkClient.NAME)),
            mapper = get(named(ResultsMapper.NAME))
        )
    }

    single<GetBirthdayListUseCase> {
        GetBirthdayListUseCaseImpl(
            birthdayListDataSource = get()
        )
    }
}