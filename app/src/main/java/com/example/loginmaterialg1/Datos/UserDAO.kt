package com.example.loginmaterialg1.Datos

import androidx.room.*
import com.example.loginmaterialg1.Model.User

@Dao
interface UserDAO {
    @Insert
    fun insertarUser(user:User)
    @Update
    fun actualizarUser(user:User)
    @Delete
    fun BorrarUser(user:User)

    @Query("SELECT * FROM user")
    fun ObtenerUser():List<User>

    @Query("SELECT * FROM user where usuario= :nomusu")
    fun VerficarNombre(nomusu:String):List<User>

    @Query("SELECT * FROM user where usuario= :nomusu AND password= :password")
    fun VerficarNombreAndPassword(nomusu:String,password:String):List<User>



}