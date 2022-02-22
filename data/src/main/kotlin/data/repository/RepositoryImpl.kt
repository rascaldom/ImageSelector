package data.repository

import data.datasource.RemoteDataSource
import domain.model.ThumbnailModel
import domain.repository.Repository
import domain.repository.wrapper.NetworkResult
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getImageList(queries: Map<String, String>): Flow<NetworkResult<ThumbnailModel>> = remoteDataSource.getImageList(queries)

    override suspend fun getVideoList(queries: Map<String, String>): Flow<NetworkResult<ThumbnailModel>> = remoteDataSource.getVideoList(queries)

}