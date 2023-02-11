package com.example.calculatorandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.calculatorandroid.databinding.ActivityMainBinding
import net.sourceforge.jeval.Evaluator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val digitos = arrayListOf<Button>()
        digitos.addAll(listOf(binding.zeroBtn, binding.oneBtn, binding.twoBtn, binding.threeBtn, binding.fourBtn,
                binding.fiveBtn, binding.sixBtn, binding.sevenBtn, binding.eightBtn, binding.nineBtn, binding.dotBtn))
        val mats = arrayListOf<Button>()
        mats.addAll(listOf(binding.sumBtn, binding.susBtn, binding.mulBtn, binding.divBtn, binding.dotBtn))

        fun calcular() {
            val evaluator = Evaluator()
            val opsResult = evaluator.evaluate(binding.ops.text.toString())
            if (opsResult.endsWith(".0")) {
                binding.result.text = opsResult.toDouble().toInt().toString()
            } else {
                binding.result.text = opsResult
            }
        }

        for (digito in digitos) {
            digito.setOnClickListener {
                binding.ops.text = "${binding.ops.text}${digito.text}"
                calcular()
            }
        }

        for (signo in mats) {
            signo.setOnClickListener {
                binding.ops.text = "${binding.ops.text}${signo.text}"
            }
        }

        binding.equalBtn.setOnClickListener {
            binding.ops.text = binding.result.text
            binding.result.text = ""
        }

        binding.clearBtn.setOnClickListener {
            binding.ops.text = ""
            binding.result.text = ""
        }

        binding.delBtn.setOnClickListener {
            binding.result.text = ""
            if (!binding.ops.text.equals(""))
                binding.ops.text = binding.ops.text.substring(0, binding.ops.text.length - 1)
        }
    }

}