package com.example.loginmaterialg1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginmaterialg1.Datos.AreaDAO
import com.example.loginmaterialg1.Datos.RolDAO
import com.example.loginmaterialg1.Datos.UserDAO
import com.example.loginmaterialg1.Datos.UserDatabase
import com.example.loginmaterialg1.Model.Area
import com.example.loginmaterialg1.Model.Rol
import com.example.loginmaterialg1.Model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registrar.*

class RegistrarActivity : AppCompatActivity() {
    lateinit var userDAO:UserDAO
    lateinit var rolDAO:RolDAO
    lateinit var areaDAO: AreaDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        userDAO = UserDatabase.getInstance(this).userDao()
        rolDAO = UserDatabase.getInstance(this).rolDao()
        areaDAO = UserDatabase.getInstance(this).areaDao()
        listRol()
        listArea()
        btnregistrar.setOnClickListener {
            val nombres=txt_name.text!!.toString()
            val usuario=txt_user.text!!.toString()
            val password=txt_password.text!!.toString()
            val rol = spinner_rol.selectedItemPosition
            val area = spinner_area.selectedItemPosition
            val oUsuario=User(0, nombres, usuario, password, rol, area)
            userDAO.insertarUser(oUsuario)
            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_LONG).show()
            finish()
        }
        txt_user.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val name = txt_user.text!!.toString()
                if(name.length<5){
                    txt_user.error="El usuario debe de tener más de 5 caracteres"
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
        txt_password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val name = txt_name.text!!.toString()
                if(name.length<5){
                    txt_password.error="El password debe de tener más de 5 caracteres"
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
    }
    fun listRol(){
        val list: ArrayList<String> = ArrayList()
        for (item in rolDAO.ObtenerRol()){
            list+= item.name
        }
        spinner_rol.adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
    }
    fun listArea(){
        val list: ArrayList<String> = ArrayList()
        for (item in areaDAO.ObtenerArea()){
            list+= item.name
        }
        spinner_area.adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
    }
}