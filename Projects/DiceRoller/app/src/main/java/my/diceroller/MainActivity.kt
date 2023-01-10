package my.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }

        // Do a dice roll when the app starts
        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRollRes = dice.roll()
        // Update the screen with the dice roll
        val diceImg: ImageView = findViewById(R.id.imageView)
        val drawerImg = when (diceRollRes) {
            //in 1..6 -> R.drawable.["dice_$diceRollRes"]
            // Determine which drawable resource ID to use based on the dice roll
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6

        }
        diceImg.contentDescription = diceRollRes.toString()
        diceImg.setImageResource(drawerImg)

    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}