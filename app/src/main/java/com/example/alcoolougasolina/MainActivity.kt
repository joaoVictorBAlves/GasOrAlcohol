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
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var percentual:Double = 0.7
    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("PDM23","No onCreate, $percentual")

        val btCalc: Button = findViewById(R.id.btCalcular)
        val switchPercent: Switch = findViewById(R.id.swPercentual)
        val edGasolina: EditText = findViewById(R.id.edGasolina)
        val edAlcool: EditText = findViewById(R.id.edAlcool)
        val resultado: TextView = findViewById(R.id.resultado)

        btCalc.setOnClickListener(View.OnClickListener {
            resultado.setText("")
            if (edGasolina.text.isNotEmpty() && edAlcool.text.isNotEmpty()) {
                var alcool: Double = edAlcool.text.toString().toDouble()
                var gasolina: Double = edGasolina.text.toString().toDouble()
                if (alcool <= gasolina*percentual) {
                    resultado.setText("Melhor comprar Álcool")
                } else {
                    resultado.setText("Melhor comprar Gasolina")
                }
            }
        })

        switchPercent.setOnClickListener(View.OnClickListener {
            percentual = if(percentual == 0.70){
                0.75
            }else{
                0.70
            }
        })
    }
override fun onResume(){
    super.onResume()
    Log.d("PDM23","No onResume, $percentual")
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