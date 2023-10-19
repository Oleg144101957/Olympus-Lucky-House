package tiv.edo.cyberobics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tiv.edo.cyberobics.databinding.NoNetworkActivityBinding

class NoNetworkActivity : AppCompatActivity() {

    private lateinit var binding: NoNetworkActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoNetworkActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRefresh.setOnClickListener {
            goToTheMainScreen()
        }

    }

    private fun goToTheMainScreen() {
        val intentToTheMainScreen = Intent(this, MainActivity::class.java)
        startActivity(intentToTheMainScreen)
    }

}