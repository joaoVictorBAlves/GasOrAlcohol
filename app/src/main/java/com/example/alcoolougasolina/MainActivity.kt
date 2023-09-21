package com.example.alcoolougasolina

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
    /**
     *  Methods that saves instances of app state
     * */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("gasValue", edGas.text.toString())
        outState.putString("alcoholValue", edAlcohol.text.toString())
        outState.putBoolean("switchState", switchPercent.isChecked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedGasValue = savedInstanceState.getString("gasValue")
        val savedAlcoholValue = savedInstanceState.getString("alcoholValue")
        Log.d("INSTANCE", "$savedGasValue and $savedAlcoholValue")
        edGas.setText(savedGasValue)
        edAlcohol.setText(savedAlcoholValue)

        // Restaurar o estado do Switch
        val switchState = savedInstanceState.getBoolean("switchState")
        switchPercent.isChecked = switchState
    }

}