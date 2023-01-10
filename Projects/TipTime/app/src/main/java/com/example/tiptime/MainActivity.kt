package com.example.tiptime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    //It's defined at this level because it will be used across
    //multiple methods in MainActivity class.
    private lateinit var binding: ActivityMainBinding
    //It's a promise that your code will initialize the variable before using it.
    // If you don't, your app will crash.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //This line initializes the binding object
        // which you'll use to access Views in the activity_main.xml layout.
        setContentView(binding.root)
        //this specifies the root of the hierarchy of views in your app, binding.root.
        //the root connects to all of parent views and child views
        binding.calculateButton.setOnClickListener { calculateTip() }
        print("This is r.string ${R.string.tip_amount}")

    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        // formatting numbers as currency，就是可以按照各國語言去format錢的格式
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        Log.i("mt", "r.string-> ${R.string.tip_amount}")


    }

    private fun calculateTip() {
        //get the cost of service
        val stringInTextField = binding.costOfService.text.toString()
        //The text attribute of an EditText is an Editable,
        // because it represents text that can be changed,
        // so we need to convert it to string
        //(Because toDouble() needs to be called on a String)
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            binding.tipResult.text = ""
            return
        }
        //get the selected option's id
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.20
            else -> 0.15
        }
        var tip = cost * tipPercentage
        //Using var coz you may need to round up the value if the user
        //selected that option, so the value might change.

        if (binding.roundUpSwitch.isChecked) tip = kotlin.math.ceil(tip)
        //round up == "進位" -> 無條件進位

        // Display the formatted tip value on screen
        displayTip(tip)

    }
}

