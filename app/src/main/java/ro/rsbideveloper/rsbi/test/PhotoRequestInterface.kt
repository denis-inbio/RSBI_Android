package ro.rsbideveloper.rsbi.test

import retrofit2.http.GET

interface PhotoRequestInterface {
    companion object {
        const val BASEURL = "https://random.dog"
    }

    @GET("/woof.json?ref=apilist.fun")
    suspend fun getRandomDog(): PhotoDataClassJson
}