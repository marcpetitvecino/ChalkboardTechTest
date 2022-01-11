package com.marcpetit.data.repository

import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RetrofitNetworkClientTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun given_configuration_when_buildClient_then_clientIsConfigured() {

        val networkClient =
            RetrofitNetworkClient()
        val client = networkClient.client()
        Assert.assertNotNull(client)
        Assert.assertNotNull(client.baseUrl())
    }
}