package com.example.alcoolougasolina

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var percent:Double = 0.7
    private lateinit var btCalc: Button
    private lateinit var switchPercent: Switch
    private lateinit var edGas: EditText
    private lateinit var edAlcohol: EditText
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements after setContentView
        btCalc = findViewById(R.id.btCalcular)
        switchPercent = findViewById(R.id.swPercent)
        edGas = findViewById(R.id.edGas)
        edAlcohol = findViewById(R.id.edAlcohol)
        result = findViewById(R.id.edResult)
        Log.d("PDM23","No onCreate, $percent")

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

        switchPercent.setOnClickListener(View.OnClickListener {
            percent = if(percent == 0.70){
                0.75
            }else{
                0.70
            }
        })
    }
override fun onResume(){
    super.onResume()
    Log.d("PDM23","No onResume, $percent")
}
override fun onStart(){
    super.onStart()
    Log.d("PDM23","No onResume")
}
override fun onPause(){
    super.onPause()
    Log.d("PDM23","No onResume")
}
override fun onStop(){
    super.onStop()
    Log.d("PDM23","No onResume")
}
override fun onDestroy(){
    super.onDestroy()
    Log.d("PDM23","No onResume")
}
}