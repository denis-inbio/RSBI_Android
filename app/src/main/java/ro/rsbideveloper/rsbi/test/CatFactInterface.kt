package ro.rsbideveloper.rsbi.test

import retrofit2.Call
import retrofit2.http.GET

interface CatFactInterface {
    companion object {
        const val BASEURL = "https://cat-fact.herokuapp.com"
    }

    @GET("/facts/random")   // <TODO> how to generate the URL for this by also including a(n) (optional) parameter ?
                                    // (such as GET parameters, $animal_type and $amount)
    fun getRandomFact(): Call<CatFactJSON>  // <TODO> interesting new thing, what's it do ? is this an "interruptible network request" (!?)
    // <TODO> this is different, this explicitly requires NOT to use `suspend fun`, but `fun`
}