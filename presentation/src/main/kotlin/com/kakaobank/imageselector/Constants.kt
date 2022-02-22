package com.kakaobank.imageselector

const val BASE_API_URL = "https://dapi.kakao.com/"

const val CONNECT_TIMEOUT = 10L
const val READ_TIMEOUT = 10L

const val IMAGE_PAGING_SIZE = 80
const val VIDEO_PAGING_SIZE = 15
const val LIST_PAGING_SIZE = IMAGE_PAGING_SIZE + VIDEO_PAGING_SIZE

object ParameterType {
    const val TYPE_QUERY = "query"
    const val TYPE_SORT = "sort"
    const val TYPE_PAGE = "page"
    const val TYPE_SIZE = "size"
}

object SortType {
    const val TYPE_ACCURACY = "accuracy"
    const val TYPE_RECENCY = "recency"
}

object PagingExceptionType {
    const val TYPE_NO_RESULT = "NO_RESULT"
    const val TYPE_NETWORK_RESPONSE_FAIL = "NETWORK_RESPONSE_FAIL"
}