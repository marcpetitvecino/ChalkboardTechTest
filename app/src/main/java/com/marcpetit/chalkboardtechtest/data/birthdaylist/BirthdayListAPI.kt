package com.marcpetit.chalkboardtechtest.data.birthdaylist

import retrofit2.http.GET

interface BirthdayListAPI {
    companion object {
        const val PATH = "/api/?results=1000&seed=chalkboard&inc=name,dob"
    }

    @GET(PATH)
    suspend fun getBirthDayList(): ResultsDTO
}