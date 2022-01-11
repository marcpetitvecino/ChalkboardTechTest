package com.marcpetit.data.repository

import com.marcpetit.data.network.NetworkClient
import retrofit2.Retrofit

open class RetrofitDataSource<API, T, DTO>(
    client: NetworkClient<Retrofit>
) : DataSource<Retrofit, T, DTO, RetrofitPredicate<API, T, DTO>>(client) {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun fetch(predicate: RetrofitPredicate<API, T, DTO>): DataResponse<T> {
        return try {
            val service = client.create(predicate.service())
            val endpoint = predicate.endpoint()
            val dto = endpoint(service)
            DataResponse.Success(predicate.mapper().mapToDomainModel(dto))
        } catch (e: Throwable) {
            DataResponse.Failure(errorHandler(e))
        }
    }
}

suspend fun <API, T, DTO> RetrofitDataSource<API, T, DTO>.save(
    predicate: RetrofitPredicate<API, T, DTO>
): DataResponse<T> {
    return fetch(predicate)
}

interface RetrofitPredicate<API, T, DTO> : Predicate<T, DTO> {
    fun service(): Class<API>
    fun endpoint(): suspend (API) -> DTO
}