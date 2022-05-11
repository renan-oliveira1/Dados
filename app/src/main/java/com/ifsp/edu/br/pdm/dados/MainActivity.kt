package com.ifsp.edu.br.pdm.dados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ifsp.edu.br.pdm.dados.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val random: Random by lazy{
        Random(System.currentTimeMillis())
    }

    private lateinit var settingsActivityResultLauncher: ActivityResultLauncher<Intent>

    private var config: Config = Config()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.buttonPlay.setOnClickListener {
            var result: Int = random.nextInt(1..config.numberFaces)
            "Face sorteada foi $result".also {
                activityMainBinding.textviewResult.text = it
            }

            var imageName = "dice_${result}"
            activityMainBinding.imageviewResultImage1.setImageResource(
                resources.getIdentifier(imageName, "mipmap", packageName)
            )

            if( config.numberDices == 2 ){
                activityMainBinding.imageviewResultImage2.visibility = View.VISIBLE

                result = random.nextInt(1..config.numberFaces)
                "${activityMainBinding.textviewResult.text} e $result".also {
                    activityMainBinding.textviewResult.text = it
                }

                imageName = "dice_${result}"
                activityMainBinding.imageviewResultImage2.setImageResource(
                    resources.getIdentifier(imageName, "mipmap", packageName)
                )
            }else{
                activityMainBinding.imageviewResultImage2.visibility = View.INVISIBLE
            }
        }

        settingsActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                if(result.data != null){
                    val configResult: Config? = result.data!!.getParcelableExtra<Config>(Intent.ACTION_CONFIGURATION_CHANGED)
                    if (configResult != null) {
                        config = configResult
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.mainmenu_settings){
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsActivityResultLauncher.launch(settingsIntent)
            return true
        }
        return true
    }
}