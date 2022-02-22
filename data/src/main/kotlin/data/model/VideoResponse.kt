package data.model

import domain.model.ThumbnailModel

data class VideoResponse(
    val documents: List<VideoDocument>,
    val meta: VideoMeta
) : ThumbnailModel {
    override val list: List<ThumbnailModel.Element>
        get() = documents
    override val metaData: ThumbnailModel.MetaData
        get() = meta

}

data class VideoDocument(
    val title: String,
    val url: String,
    val datetime: String,
    val play_time: Long,
    val thumbnail: String,
    val author: String,
) : ThumbnailModel.Element {
    override val thumbnailUrl: String
        get() = thumbnail
    override val content: String
        get() = title
    override val dateTime: String
        get() = datetime
}

data class VideoMeta(
    val is_end: Boolean,
    val pageable_count: Long,
    val total_count: Long
) : ThumbnailModel.MetaData {
    override val isEnd: Boolean
        get() = is_end

}
