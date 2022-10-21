package io.github.hse.android

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.github.hse.android.databinding.ActivitySecondBinding
import io.github.hse.android.MainActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBackButton()
        observeLocalisationChange()
    }

    private fun observeLocalisationChange() {
        getLocaleChangedFlow(baseContext)
            .onEach {
                showToastMessage("LocaleChanged")
            }
            .launchIn(MainScope())
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        screenConfiguration(newConfig.orientation)
        keyboardConfiguration(newConfig.keyboardHidden)
        super.onConfigurationChanged(newConfig)
    }

    private fun screenConfiguration(config: Int) = when(config) {
        Configuration.ORIENTATION_LANDSCAPE -> { showToastMessage("Landscape Mode") }
        Configuration.ORIENTATION_PORTRAIT -> { showToastMessage("Portrait Mode") }
        Configuration.ORIENTATION_SQUARE -> {}
        Configuration.ORIENTATION_UNDEFINED -> {}
        else -> {}
    }

    private fun keyboardConfiguration(config: Int) = when(config) {
        Configuration.HARDKEYBOARDHIDDEN_NO -> { showToastMessage("Keyboard visible") }
        Configuration.HARDKEYBOARDHIDDEN_YES -> { showToastMessage("Keyboard hidden") }
        else -> {}
    }

    private fun showToastMessage(message:String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }
}