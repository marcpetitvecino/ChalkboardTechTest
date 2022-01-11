package com.marcpetit.data.repository

import com.marcpetit.data.network.NetworkClient

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T) : DataResponse<T>()
    data class Failure(val error: Throwable) : DataResponse<Nothing>()

    val isFailure get() = this is Failure
    val isSuccess get() = this is Success
}

fun <T, R> DataResponse<R>.map(fn: (R) -> T): DataResponse<T> =
    when (this) {
        is DataResponse.Failure -> DataResponse.Failure(error)
        is DataResponse.Success -> DataResponse.Success(fn(data))
    }

fun <T, R> DataResponse<R>.flatMap(fn: (R) -> DataResponse<T>): DataResponse<T> =
    when (this) {
        is DataResponse.Failure -> DataResponse.Failure(error)
        is DataResponse.Success -> fn(data)
    }

fun <T, R> DataResponse<R>.fold(foldFailure: (Throwable) -> T, foldSuccess: (R) -> T): T =
    when (this) {
        is DataResponse.Failure -> foldFailure(error)
        is DataResponse.Success -> foldSuccess(data)
    }

fun interface Predicate<T, DTO> {
    fun mapper(): Mapper<T, DTO>
}

fun interface Mapper<T, DTO> {
    fun mapToDomainModel(dto: DTO): T
}

abstract class DataSource<Client, out T, DTO, in P>(networkClient: NetworkClient<Client>) {

    val client: Client = networkClient.client()

    abstract suspend fun fetch(predicate: P): DataResponse<T>

    open fun errorHandler(e: Throwable): Throwable {
        return e
    }

}

