package com.bersyte.chips

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bersyte.chips.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chip1.setOnClickListener {
            Toast.makeText(
                this,
                binding.chip1.text,
                Toast.LENGTH_SHORT
            ).show()
        }
        //entry(input) chips
        entryChips()

        //choice chips
        choiceChip()

        //FilterChip
        filterChips()

    }

    private fun creteMyChips(txt: String) {
        val chip = Chip(this)
        chip.apply {
            text = txt
            chipIcon = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.ic_launcher_background)
            isChipIconVisible = false
            isCloseIconVisible = true
            isClickable = true
            isCheckable = false
            binding.chipGroup.addView(chip as View)
            setOnCloseIconClickListener {
                binding.chipGroup.removeView(chip as View)
            }
        }
    }

    private fun entryChips() {
        binding.etNames
            .setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER
                    && event.action == KeyEvent.ACTION_UP
                ) {

                    val name = binding.etNames.text.toString()
                    creteMyChips(name)
                    binding.etNames.text.clear()
                    return@setOnKeyListener true
                }
                false
            }
    }

    private fun choiceChip() {

        binding.choiceChipGroup
            .setOnCheckedChangeListener { group, checkedId ->

                val chip: Chip? =
                    group.findViewById(checkedId)

                chip?.let {
                    Toast.makeText(
                        this,
                        chip.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
    private fun filterChips() {
        binding.chipGroupFilter
            .setOnCheckedChangeListener { group, checkedId ->
                val chip: Chip? = group.findViewById(checkedId)
                chip?.chipBackgroundColor = getColorStateList(
                        R.color.purple_200)
                if (chip?.isChecked == true) {
                    Toast.makeText(
                        this,
                        chip.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}