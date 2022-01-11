package com.marcpetit.chalkboardtechtest.data.birthdaylist

import android.net.Network
import com.marcpetit.chalkboardtechtest.domain.birthday.BirthdayDomainModel
import com.marcpetit.chalkboardtechtest.domain.birthdaylist.BirthdayListDataSource
import com.marcpetit.data.network.NetworkClient
import com.marcpetit.data.repository.DataResponse
import com.marcpetit.data.repository.Mapper
import com.marcpetit.data.repository.RetrofitDataSource
import retrofit2.Retrofit

class BirthdayListDataSourceImpl(
    client: NetworkClient<Retrofit>,
    private val mapper: Mapper<List<BirthdayDomainModel>, ResultsDTO>
): BirthdayListDataSource, RetrofitDataSource<BirthdayListAPI, List<BirthdayDomainModel>, ResultsDTO>(client) {
    override suspend fun getBirthdayList(): DataResponse<List<BirthdayDomainModel>> {
        return fetch(BirthdayListPredicate(mapper))
    }
}