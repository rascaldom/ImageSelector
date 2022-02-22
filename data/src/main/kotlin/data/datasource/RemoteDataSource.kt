package data.datasource

import data.model.ImageResponse
import data.model.VideoResponse
import domain.repository.wrapper.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getImageList(queries: Map<String, String>): Flow<NetworkResult<ImageResponse>>

    suspend fun getVideoList(queries: Map<String, String>): Flow<NetworkResult<VideoResponse>>

}