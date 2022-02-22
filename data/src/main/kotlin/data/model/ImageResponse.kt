package data.model

import domain.model.ThumbnailModel

data class ImageResponse(
    val documents: List<ImageDocument>,
    val meta: ImageMeta
) : ThumbnailModel {
    override val list: List<ThumbnailModel.Element>
        get() = documents
    override val metaData: ThumbnailModel.MetaData
        get() = meta
}

data class ImageDocument(
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val width: Long,
    val height: Long,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String,
) : ThumbnailModel.Element {
    override val thumbnailUrl: String
        get() = thumbnail_url
    override val content: String
        get() = display_sitename
    override val dateTime: String
        get() = datetime
}

data class ImageMeta(
    val is_end: Boolean,
    val pageable_count: Long,
    val total_count: Long
) : ThumbnailModel.MetaData {
    override val isEnd: Boolean
        get() = is_end

}
