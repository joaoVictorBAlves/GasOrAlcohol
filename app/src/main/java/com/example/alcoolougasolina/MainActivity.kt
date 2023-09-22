package com.example.alcoolougasolina

import android.content.Context
import android.content.SharedPreferences
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    private var percent:Double = 0.7
    private lateinit var btCalc: Button
    private lateinit var switchPercent: Switch
    private lateinit var edGas: EditText
    private lateinit var edAlcohol: EditText
    private lateinit var result: TextView

    private lateinit var image: ImageView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)

        image = findViewById(R.id.imageView)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            Log.d("ESCURO", "Está escuro")
            val matrix = ColorMatrix().apply {
                setScale(1.2f, 1.2f, 1.2f, 1f)
                setSaturation(0f)
            }
            val filter = ColorMatrixColorFilter(matrix)
            image.colorFilter = filter
        }

        // Initialize UI elements after setContentView
        btCalc = findViewById(R.id.btCalcular)
        switchPercent = findViewById(R.id.swPercent)
        edGas = findViewById(R.id.edGas)
        edAlcohol = findViewById(R.id.edAlcohol)
        result = findViewById(R.id.edResult)

        /**
         * Recover state
         * */
        edGas.setText(sharedPreferences.getString("gasValue", ""))
        edAlcohol.setText(sharedPreferences.getString("alcoholValue", ""))
        result.text = sharedPreferences.getString("result", "")
        switchPercent.isChecked = sharedPreferences.getBoolean("switchState", false)
        /**
         * When click in button the operation runs
         */
        btCalc.setOnClickListener(View.OnClickListener {
            result.text = ""
            if (edGas.text.isNotEmpty() && edAlcohol.text.isNotEmpty()) {
                val alcohol: Double = edAlcohol.text.toString().toDouble()
                val gas: Double = edGas.text.toString().toDouble()
                if (alcohol <= gas * percent) {
                    result.text = "It's better to buy Alcohol"
                } else {
                    result.text = "It's better to buy Gas"
                }
            }
        })
        /**
         * Change the factor of operation
         * */
        switchPercent.setOnClickListener(View.OnClickListener {
            percent = if(percent == 0.70){
                0.75
            }else{
                0.70
            }
        })
    }
    /**
     *  Methods that saves instances of app state
     * */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(sharedPreferences.edit()) {
            putString("gasValue", edGas.text.toString())
            putString("alcoholValue", edAlcohol.text.toString())
            putString("result", result.text.toString())
            putBoolean("switchState", switchPercent.isChecked)
            apply()
        }
    }
}