package io.github.hse.android


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.hse.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigateButton()
    }

    override fun onResume() {
        getIntentMessage()
        super.onResume()
    }

    private fun getIntentMessage() {
        val intent = intent.getStringExtra(Constants.MESSAGE_KEY)
        intent?.let { showToastMessage(it) }
    }

    private fun initNavigateButton() {
        binding.nextButton.setOnClickListener {
            navigateToSecondActivity()
        }
    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun showToastMessage(message:String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }

}


