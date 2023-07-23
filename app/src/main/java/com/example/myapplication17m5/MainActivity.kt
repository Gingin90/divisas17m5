package com.example.myapplication17m5
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.myapplication17m5.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var divisas = listOf<String>("USD","CLP","EUR")
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
            val monto :Double = binding.editTextEntrada.text.toString().toDouble()
            val divisaActual: String= binding.spinnerUno.selectedItem.toString()
            val divisaCambio :String = binding.spinnerDos.selectedItem.toString()
          Log.d("estamos en el init listener","  $monto,$divisaActual,$divisaCambio")
            val resultado :Double = conversorDivisas(monto,divisaActual,divisaCambio)
            binding.textVinTo.text= resultado.toString()

        }
        binding.buttonResert.setOnClickListener{
            limpiar()
        }

    }
    fun conversorDivisas(monto:Double,divisaActual :String,divisaCambio: String): Double {
        var resultado = monto
        when (divisaActual) {
            "USD" -> {
                if (divisaCambio == "CLP") {
                    resultado = monto * 817
                } else if (divisaCambio == "USD") {
                    resultado = monto * 1
                } else if (divisaCambio == "EUR") {
                    resultado = monto * 0.98
                }
            }

            "CLP" -> if (divisaCambio == "CLP") {
                resultado = monto * 1
            } else if (divisaCambio == "USD") {
                monto * 0.001
            } else if (divisaCambio == "EUR") {
                resultado = monto * 0.001
            }

        "EUR" -> if (divisaCambio == "CLP") {
            resultado = monto * 910
        } else if (divisaCambio == "USD") {
            resultado = monto * 1.11
        } else if (divisaCambio == "EUR") {
            resultado = monto * 1
        }


            else -> { resultado = monto
            }
        }

        return resultado
    }
      fun limpiar(){

            binding.textVinTo.text = ""
            binding.editTextEntrada.setText("")

        }

    }













