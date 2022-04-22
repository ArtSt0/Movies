package com.example.movies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.movies.viewModels.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val vmSplash: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            val content = findViewById<View>(android.R.id.content)
            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {

                        return if (vmSplash.getIsReady()) {
                            openActivity()
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else {
                            false
                        }
                    }
                })
        }else{
            //задержка по времени и открытие активити
            lifecycleScope.launch {
                delay(1000)
                openActivity()
            }
        }
    }

    //открытие активити
    private fun openActivity(){
        val intentMain = Intent(this, MainActivity::class.java)
        startActivity(intentMain)
        finish()
    }
}