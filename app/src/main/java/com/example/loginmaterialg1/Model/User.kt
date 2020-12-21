package com.example.loginmaterialg1.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) var id:Int,
    val nombres:String,
    val usuario:String,
    val password:String,
    val rol_id: Int,
    val area_id: Int
)