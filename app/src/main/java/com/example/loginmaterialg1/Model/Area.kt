package com.example.loginmaterialg1.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Area (
    @PrimaryKey(autoGenerate = true) var id:Int,
    val name:String,
    val state:Int
)