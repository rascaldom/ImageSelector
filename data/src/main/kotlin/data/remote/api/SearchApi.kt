package data.remote.api

import data.HEADER_NAME_AUTHORIZATION
import data.model.ImageResponse
import data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface SearchApi {

    @GET("/v2/search/image")
    suspend fun getImageList(@Header(HEADER_NAME_AUTHORIZATION) appKey: String, @QueryMap queryMap: Map<String, String>): Response<ImageResponse>

    @GET("/v2/search/vclip")
    suspend fun getVideoList(@Header(HEADER_NAME_AUTHORIZATION) appKey: String, @QueryMap queryMap: Map<String, String>): Response<VideoResponse>

}