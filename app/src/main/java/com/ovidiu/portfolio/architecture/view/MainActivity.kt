package com.ovidiu.portfolio.architecture.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ovidiu.portfolio.MainApplication
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.model.data_source.local.LocalDataBase
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var db: LocalDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)
    }
}
