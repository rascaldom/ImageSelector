package data.datasource

import data.AUTHORIZATION
import data.model.ImageResponse
import data.model.VideoResponse
import data.remote.ApiResponse
import data.remote.api.SearchApi
import domain.repository.wrapper.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(private val searchApi: SearchApi) : RemoteDataSource, ApiResponse() {

    override suspend fun getImageList(queries: Map<String, String>): Flow<NetworkResult<ImageResponse>> =
        flow {
            emit( safeApiCall { searchApi.getImageList(AUTHORIZATION, queries) } )
        }

    override suspend fun getVideoList(queries: Map<String, String>): Flow<NetworkResult<VideoResponse>> =
        flow {
            emit( safeApiCall { searchApi.getVideoList(AUTHORIZATION, queries) } )
        }

}