package com.android.example.music.models

import android.content.ContentResolver

class SongProvider(contentResolver: ContentResolver) {

    private val cursor = contentResolver.query(
        RawFilesProvider.CONTENT_URI,
        null,
        null,
        null,
        null
    )

    fun getSongsList(): List<String> {
        val newSongsList = mutableListOf<String>()

        cursor?.let {
            val uriColumn = cursor.getColumnIndex(RawFilesProvider.URI)

            if (uriColumn == -1) {
                return arrayListOf()
            }
            if (cursor.moveToFirst()) {
                do {
                    newSongsList.add(cursor.getString(uriColumn))
                } while (cursor.moveToNext())
            }
        }
        return newSongsList
    }
}
