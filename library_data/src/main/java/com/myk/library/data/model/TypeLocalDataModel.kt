package com.myk.library.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeLocalDataModel(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String
)
