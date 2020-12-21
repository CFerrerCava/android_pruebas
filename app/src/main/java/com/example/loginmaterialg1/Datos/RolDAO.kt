package com.example.loginmaterialg1.Datos

import androidx.room.*
import com.example.loginmaterialg1.Model.Rol

@Dao
interface RolDAO {
    @Insert
    fun insertarRol(rol:Rol)

    @Query("SELECT * FROM rol")
    fun ObtenerRol():List<Rol>


}