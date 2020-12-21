package com.example.loginmaterialg1.Datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginmaterialg1.MainActivity
import com.example.loginmaterialg1.Model.Area
import com.example.loginmaterialg1.Model.Rol
import com.example.loginmaterialg1.Model.User

@Database(entities = [User::class, Area::class, Rol::class],version=1)
abstract  class UserDatabase:RoomDatabase() {
    abstract fun userDao():UserDAO
    abstract fun rolDao():RolDAO
    abstract fun areaDao():AreaDAO
    companion object{
        private var INSTANCE:UserDatabase?=null
            fun getInstance(context: Context):UserDatabase{
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,"bdVentas"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    dataOfNull()
                }
                return INSTANCE!!
            }
        fun dataOfNull(){
            var rolDAO = INSTANCE!!.rolDao()
            var areaDAO = INSTANCE!!.areaDao()
            var list1 = rolDAO.ObtenerRol()
            var list2= areaDAO.ObtenerArea()
            if (list1.isEmpty()){
                rolDAO.insertarRol(Rol(0,"Administrador",1))
                rolDAO.insertarRol(Rol(0,"Editor",1))
            }
            if (list2.isEmpty()){
                areaDAO.insertarArea(Area(0,"Logística",1))
                areaDAO.insertarArea(Area(0,"I&T",1))
            }
        }
    }
}