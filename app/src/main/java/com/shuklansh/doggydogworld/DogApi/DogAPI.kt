package com.shuklansh.doggydogworld.DogApi

import com.shuklansh.doggydogworld.Model.dogModel
import retrofit2.Response
import retrofit2.http.GET

interface DogAPI {
    @GET("api/breeds/image/random")
    suspend fun getDogImgLink():Response<dogModel>

}