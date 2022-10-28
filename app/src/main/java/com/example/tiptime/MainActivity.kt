package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.tiptime.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showTip()
    }

    override fun onResume() {
        super.onResume()
        sugTip()
    }

    private fun sugTip() {
        with(binding) {
            btCalcular.setOnClickListener {
                val mountTotal = binding.costOfServiceEditText.editText?.text.toString().toInt()
                val tip = when {
                    rbAmazing.isChecked -> mountTotal + (mountTotal * 0.2)
                    rbGood.isChecked -> mountTotal + (mountTotal * 0.18)
                    rbOk.isChecked -> mountTotal + (mountTotal * 0.15)
                    swTip.isChecked -> mountTotal
                    else -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Deberías brindar una propina",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                if (tip == Unit) {
                    sugTip.text = "Total a pagar $mountTotal sin propina"
                } else {
                    sugTip.text = "Total a pagar ${tip.toString()} con propina incluida"
                }
            }
        }
    }

    private fun showTip() {
        with(binding) {
            swTip.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked) {
                    rbGroup.isVisible = true
                    rbAmazing.isChecked = true
                }else{
                    rbAmazing.isChecked = false
                    rbGood.isChecked = false
                    rbOk.isChecked = false
                    rbGroup.isVisible = false
                }
            }
        }
    }

}