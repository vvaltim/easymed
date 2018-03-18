package br.com.waltervjunior.easymed.vc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson

class SplashActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        //verificando se o cara ta logado
        val currentUser : FirebaseUser? = mAuth.currentUser
        Log.d("Firebase currentUser", Gson().toJson(currentUser))
        if(currentUser != null){
            //usuário logado, mandar pra tela inicial (VALIDAR SE ELE E DOCTOR OU PACIENT)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
        }else{
            //mandar o usuário pra login
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
    }
}