package domain.usecase

import domain.model.ThumbnailModel
import domain.repository.Repository
import domain.repository.wrapper.NetworkResult
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: Repository) {

    suspend fun requestImageList(queries: Map<String, String>): Flow<NetworkResult<ThumbnailModel>> = repository.getImageList(queries)

    suspend fun requestVideoList(queries: Map<String, String>): Flow<NetworkResult<ThumbnailModel>> = repository.getVideoList(queries)

}