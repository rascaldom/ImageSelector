package com.kakaobank.imageselector.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kakaobank.imageselector.IMAGE_PAGING_SIZE
import com.kakaobank.imageselector.PagingExceptionType
import com.kakaobank.imageselector.ParameterType
import com.kakaobank.imageselector.VIDEO_PAGING_SIZE
import domain.model.ThumbnailModel
import domain.repository.wrapper.NetworkResult
import domain.usecase.SearchUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.single

class SearchPagingSource(private val searchUseCase: SearchUseCase, private val queries: Map<String, String>) : PagingSource<Int, ThumbnailModel.Element>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ThumbnailModel.Element> {
        try {
            val page = params.key ?: 1
            var isEnd = true
            val list = ArrayList<ThumbnailModel.Element>()
            return coroutineScope {
                listOf(
                    async { searchUseCase.requestImageList(queries.plus(mapOf(
                        ParameterType.TYPE_PAGE to page.toString(),
                        ParameterType.TYPE_SIZE to IMAGE_PAGING_SIZE.toString()))).single() },
                    async { searchUseCase.requestVideoList(queries.plus(mapOf(
                        ParameterType.TYPE_PAGE to page.toString(),
                        ParameterType.TYPE_SIZE to VIDEO_PAGING_SIZE.toString()))).single() }
                ).awaitAll().map {
                    when (it) {
                        is NetworkResult.Success -> {
                            if (it.data.list.isNotEmpty()) {
                                list.addAll(it.data.list)
                                isEnd = isEnd.and(it.data.metaData.isEnd)
                            } else {
                                throw Exception(PagingExceptionType.TYPE_NO_RESULT)
                            }
                        }
                        is NetworkResult.Error -> {
                            throw Exception(it.message)
                        }
                        else -> {
                            throw Exception(PagingExceptionType.TYPE_NETWORK_RESPONSE_FAIL)
                        }
                    }
                }
                LoadResult.Page(
                    data = list.sortedByDescending { it.dateTime },
                    prevKey = null,
                    nextKey = if (isEnd) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ThumbnailModel.Element>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}