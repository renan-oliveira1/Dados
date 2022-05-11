package com.ifsp.edu.br.pdm.dados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ifsp.edu.br.pdm.dados.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private val activitySettingsBinding: ActivitySettingsBinding by lazy{
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitySettingsBinding.root)

        activitySettingsBinding.buttonSave.setOnClickListener {
            val numberDices: Int = (activitySettingsBinding.spinnerNumberDices.selectedView as TextView)
                .text
                .toString()
                .toInt()
            val numberFaces: Int = activitySettingsBinding.edittextNumberFaces
                .text
                .toString()
                .toInt()

            val config = Config(numberDices, numberFaces)
            val resultIntent = Intent()
            resultIntent.putExtra(Intent.ACTION_CONFIGURATION_CHANGED, config)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}