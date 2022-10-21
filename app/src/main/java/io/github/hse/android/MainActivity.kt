package io.github.hse.android


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.hse.android.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigateButton()
        observeLocalisationChange()
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

    private fun observeLocalisationChange() {
        getLocaleChangedFlow(baseContext)
            .onEach {
                showToastMessage("LocaleChanged")
            }
            .launchIn(MainScope())
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        screenConfiguration(newConfig.orientation)
    }

    private fun screenConfiguration(config: Int) = when(config) {
        Configuration.ORIENTATION_LANDSCAPE -> { showToastMessage("Landscape Mode") }
        Configuration.ORIENTATION_PORTRAIT -> { showToastMessage("Portrait Mode") }
        Configuration.ORIENTATION_SQUARE -> {}
        Configuration.ORIENTATION_UNDEFINED -> {}
        else -> {}
    }


}


