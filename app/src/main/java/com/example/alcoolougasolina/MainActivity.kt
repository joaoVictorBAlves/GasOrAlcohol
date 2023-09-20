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
    private val btCalc: Button = findViewById(R.id.btCalcular)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private val switchPercent: Switch = findViewById(R.id.swPercent)
    private val edGas: EditText = findViewById(R.id.edGas)
    private val edAlcohol: EditText = findViewById(R.id.edAlcohol)
    private val result: TextView = findViewById(R.id.edResult)

    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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