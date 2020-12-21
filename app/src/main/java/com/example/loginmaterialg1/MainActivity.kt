package com.example.loginmaterialg1

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.se.omapi.Session
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginmaterialg1.Datos.UserDAO
import com.example.loginmaterialg1.Datos.UserDatabase
import com.example.loginmaterialg1.Model.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registrar.*
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    lateinit var userDao:UserDAO
    lateinit var userEntity:List<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDao=UserDatabase.getInstance(this).userDao()
        btninicio.setOnClickListener {
            val usuario=txtusuario.editText!!.text.toString()
            val password=txtpassword.editText!!.text.toString()
            val usuarioValidado=validarUsuario(usuario)
            if(!usuarioValidado){
                txtusuario.error="El usuario debe menos de 15 caracteres"
            }else{
                txtusuario.error=null
            }
            val passwordValido=validarPassword(password)
            if(!passwordValido){
                txtpassword.error="El password debe ser mas de 5 caracteres"
            }
            else{
                txtpassword.error=null
            }
            if(usuarioValidado && passwordValido){
                //Verificar si usuario es correcto
                userEntity=userDao.VerficarNombre(usuario)
                if(userEntity.size==0){
                    Toast.makeText(this, "Usuario no valido", Toast.LENGTH_LONG).show()
                }
                else
                {
                    userEntity=userDao.VerficarNombreAndPassword(usuario, password)
                    if (userEntity.size==0){
                        Toast.makeText(this, "Password no valido", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        var intent = Intent(this, MenuActivity::class.java)
                        val  sharedpreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE);
                        val editor:Editor = sharedpreferences.edit();
                        editor.putString("user_name",userEntity[0].nombres)
                        editor.putString("user_user",userEntity[0].usuario)
                        editor.commit()
                        startActivity(intent)

                    }
                }
            }

        }
        txtRegistrarse.setOnClickListener {
            startActivity(Intent(this, RegistrarActivity::class.java))
        }

    }
    fun validarUsuario(usuario: String):Boolean{
        return usuario.length<=15
    }
    fun validarPassword(password: String):Boolean{
        return password.length>=5
    }
}