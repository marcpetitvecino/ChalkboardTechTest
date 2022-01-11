package com.marcpetit.data.repository

import org.junit.Assert.*
import org.junit.Test

class DataResponseTest {

    private lateinit var response: DataResponse<Int>

    @Test
    fun `map should lift a function into context`() {
        val expected = "1"
        val value = 1

        response = DataResponse.Success(value)

        when (val result = response.map(Int::toString)) {
            is DataResponse.Success -> assertEquals(expected, result.data)
            is DataResponse.Failure -> assert(false)
        }
    }

    @Test
    fun `map should pass the error if Failure`() {

        response = DataResponse.Failure(Exception())

        when (val result = response.map(Int::toString)) {
            is DataResponse.Success -> assert(false)
            is DataResponse.Failure -> result.error is Exception
        }
    }

    @Test
    fun `flatMap should chain functions that produce DataResponse objects`() {
        val value = 2
        val expected = 1

        val stubUseCase: (Int) -> DataResponse<String> = {
            DataResponse.Success((it * 2).toString())
        }

        val stubUseCase2: (String) -> DataResponse<Int> = {
            DataResponse.Success(it.length)
        }

        response = DataResponse.Success(value)

        when (val result = response.flatMap(stubUseCase).flatMap(stubUseCase2)) {
            is DataResponse.Success -> assertEquals(expected, result.data)
            is DataResponse.Failure -> assert(false)
        }
    }

    @Test
    fun `flatMap should pass the error if Failure`() {
        val useCase: (Int) -> DataResponse<Int> = {
            DataResponse.Success(it * 2)
        }

        response = DataResponse.Failure(Exception())

        when (val result = response.flatMap(useCase).flatMap(useCase)) {
            is DataResponse.Success -> assert(false)
            is DataResponse.Failure -> result.error is Exception
        }
    }

    @Test
    fun `fold should treat DataResponse output as a pure function`() {
        val expected = 1
        val value = 1

        response = DataResponse.Success(value)

        val result = response.fold(
            {
                assert(false)
                return@fold 0
            },
            {
                it
            }
        )

        assertEquals(expected, result)
    }

    @Test
    fun `fold should treat DataResponse output as a pure function even if Failure`() {
        val expected = 0

        response = DataResponse.Failure(Exception())

        val result = response.fold(
            {
                return@fold 0
            },
            {
                assert(false)
                it
            }
        )

        assertEquals(expected, result)
    }
}