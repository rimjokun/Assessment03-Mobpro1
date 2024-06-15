package org.d3if3032.assessment03.model

data class Anime(
    val anime_id: Int,
    val user_email: String,
    val judul_anime: String,
    val episode: String,
    val musim: String,
    val image_id: String,
    val delete_hash: String,
)

data class AnimeCreate(
    val user_email: String,
    val judul_anime: String,
    val episode: String,
    val musim: String,
    val image_id: String,
    val delete_hash: String
)
