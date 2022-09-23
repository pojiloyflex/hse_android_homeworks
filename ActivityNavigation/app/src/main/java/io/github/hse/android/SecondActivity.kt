package io.github.hse.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.hse.android.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBackButton()
    }

    private fun initBackButton() {
        binding.finishButton.setOnClickListener {
            val message = binding.textInput.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(Constants.MESSAGE_KEY, message)
            }
            startActivity(intent)
        }
    }
}