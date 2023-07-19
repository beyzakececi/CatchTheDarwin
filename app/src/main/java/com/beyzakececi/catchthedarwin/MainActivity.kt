package com.beyzakececi.catchthedarwin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.beyzakececi.catchthedarwin.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score=0
    var imageArray=ArrayList<ImageView>()
    var runnable= Runnable {  }
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)
        hideImages()

        //countdowntimer

        object : CountDownTimer(30500,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText2.text="Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.timeText2.text="Time: 0"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                //alert dialog
                val alert= AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!!")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialog,which ->
                    //restart
                    val intent=intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }

                alert.show()
            }
        }.start()
    }

    fun hideImages(){

        runnable=object : Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random = Random
                val randomIndex=random.nextInt(16)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)


    }
    fun increaseScore(view: View){
        score+=1
        binding.scoreText.text="Score: ${score}"
    }
}