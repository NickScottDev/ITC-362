package com.scott.msu.photogallery.api

import com.scott.msu.photogallery.api.FlickrResponse
import retrofit2.http.GET

private const val API_KEY = "80c5f2e05728958009c26f6013ebd3db"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}
