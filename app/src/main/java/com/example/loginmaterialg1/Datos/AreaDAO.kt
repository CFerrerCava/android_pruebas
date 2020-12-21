package com.example.loginmaterialg1.Datos

import androidx.room.*
import com.example.loginmaterialg1.Model.Area

@Dao
interface AreaDAO {
    @Insert
    fun insertarArea(area:Area)

    @Query("SELECT * FROM area")
    fun ObtenerArea():List<Area>


}