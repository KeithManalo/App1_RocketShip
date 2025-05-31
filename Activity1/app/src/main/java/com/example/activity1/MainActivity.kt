package com.example.activity1

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activity1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fuelInput = findViewById<EditText>(R.id.fuelInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)
        val thrustInput = findViewById<EditText>(R.id.thrustInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)
        val instruction = findViewById<Button>(R.id.instruction)

        val button1 = findViewById<Button>(R.id.calculateButton)
        button1.setBackgroundColor(Color.parseColor("#FFFFFF"))

        val button2 = findViewById<Button>(R.id.instruction)
        button2.setBackgroundColor(Color.parseColor("#FFFFFF"))





        calculateButton.setOnClickListener {
            val fuel = fuelInput.text.toString().toDoubleOrNull() ?: 0.0
            val weight = weightInput.text.toString().toDoubleOrNull() ?: 0.0
            val thrust = thrustInput.text.toString().toDoubleOrNull() ?: 0.0

            if (weight <= 0 || thrust <= 0) {
                resultText.text = "Please enter valid weight and thrust values."
                return@setOnClickListener
            }

            val totalWeight = weight + fuel
            val thrustToWeightRatio = thrust / (totalWeight * 9.81) // gravity

            val canLaunch = thrustToWeightRatio > 1.0

            val result = """
                Total Weight: ${"%.2f".format(totalWeight)} kg
                Thrust-to-Weight Ratio: ${"%.2f".format(thrustToWeightRatio)}
                Can Launch? ${if (canLaunch) "Yes 🚀" else "No ❌"}
            """.trimIndent()

            resultText.text = result



        }
        instruction.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Instruction")
            builder.setMessage("This Calculate the Rocket Ship Launch by using Total mass, Thrust, and Gravity\n" +
                    "( Weight + Fuel = Total Mass )\n" +
                    "Thrust to Weight Ratio: \n" +
                    "( Thrust / Total mass * Earth's Gravity )")
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()



        }





    }
}