package com.example.myapplication17m5
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.myapplication17m5.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var divisas = listOf<String>("USD,CLP,EUR")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.spinnerUno.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.spinnerDos.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        initListener()
    }

    private fun initListener() {
        binding.buttonConvert.setOnClickListener{
            val monto = binding.editTextEntrada.text.toString().toDouble()
            val divisaActual= binding.spinnerUno.selectedItem.toString()
            val divisaCambio= binding.spinnerDos.selectedItem.toString()
          Log.d("estamos en el init listener","  $monto,$divisaActual,$divisaCambio")
            val resultado = conversorDivisas(monto,divisaActual,divisaCambio)

        }
        binding.buttonResert.setOnClickListener{
            limpiar()
        }

    }
    fun conversorDivisas(monto:Double,divisaActual :String,divisaCambio: String): Double {
        when (divisaActual) {
            "USD" -> {
                if (divisaCambio == "CLP") {
                    monto * 817
                } else if (divisaCambio == "USD") {
                    monto * 1
                } else if (divisaCambio == "EUR") {
                    monto * 0.98
                }
            }

            "CLP" -> if (divisaCambio == "CLP") {
                monto * 1
            } else if (divisaCambio == "USD") {
                monto * 0.001
            } else if (divisaCambio == "EUR") {
                monto * 0.001
            }

        "EUR" -> if (divisaCambio == "CLP") {
            monto * 910
        } else if (divisaCambio == "USD") {
            monto * 1.11
        } else if (divisaCambio == "EUR") {
            monto * 1
        }


            else -> { monto
            }
        }

        return monto
    }
      fun limpiar(){

            binding.textVinTo.text = ""
        binding.editTextEntrada.setText("")

        }

    }













