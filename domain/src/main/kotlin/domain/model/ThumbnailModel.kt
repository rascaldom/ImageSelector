package domain.model

interface ThumbnailModel {

    val list: List<Element>
    val metaData: MetaData

    interface Element {
        val thumbnailUrl: String
        val content: String
        val dateTime: String
    }

    interface MetaData {
        val isEnd: Boolean
    }

}