package domain.repository

import domain.model.ThumbnailModel
import domain.repository.wrapper.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getImageList(queries: Map<String, String>): Flow<NetworkResult<ThumbnailModel>>

    suspend fun getVideoList(queries: Map<String, String>): Flow<NetworkResult<ThumbnailModel>>

}