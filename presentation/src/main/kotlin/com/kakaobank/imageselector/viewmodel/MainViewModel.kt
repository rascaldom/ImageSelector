package com.kakaobank.imageselector.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kakaobank.imageselector.LIST_PAGING_SIZE
import com.kakaobank.imageselector.ParameterType
import com.kakaobank.imageselector.SortType
import com.kakaobank.imageselector.paging.SearchPagingSource
import domain.model.ThumbnailModel
import domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MainViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val queryLiveData = MutableLiveData<Map<String, String>>()

    val thumbnailResult: LiveData<PagingData<ThumbnailModel.Element>> = queryLiveData.switchMap {
        Pager(
            config = PagingConfig(LIST_PAGING_SIZE),
            pagingSourceFactory = {
                SearchPagingSource(searchUseCase, it)
            }
        ).flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope).asLiveData()
    }

    fun requestImageList(query: String) {
        queryLiveData.value = mapOf(
            ParameterType.TYPE_QUERY to query,
            ParameterType.TYPE_SORT to SortType.TYPE_RECENCY
        )
    }

    private val _bookmarksList = MutableStateFlow<LinkedHashMap<String, ThumbnailModel.Element>>(linkedMapOf())

    val bookmarksList = _bookmarksList.asStateFlow()

    fun addBookmark(item: ThumbnailModel.Element): Boolean {
        if (_bookmarksList.value.containsKey(item.thumbnailUrl)) {
            return false
        }

        _bookmarksList.update {
            (it.clone() as LinkedHashMap<String, ThumbnailModel.Element>).apply {
                this[item.thumbnailUrl] = item
            }
        }

        return true
    }

}