package domain.repository.wrapper

sealed class NetworkResult<out T> {
    class Success<out T>(val data: T) : NetworkResult<T>()
    class Error(val message: String?) : NetworkResult<Nothing>()
}