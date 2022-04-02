package com.ange.tp_rendu_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ange.tp_rendu_kotlin.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Déclaration du binding contenant les références des composants
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //Acces à mes données
    val model by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Affichage de l'interface graphique
        setContentView(binding.root)

        model.loadData()

        model.data.observe(this){
            if(it !=null && !it.gif_count.toString().isNullOrEmpty() && !it.image_count.toString().isNullOrEmpty()){
                binding.tvCountgif.text = "Le nombre de gif de canard trouvable est de " + it.gif_count + "."
                binding.tvCountimg.text = "Le nombre d'image de canard trouvable est de " + it.image_count + "."
                Picasso.get().load("https://random-d.uk/api/http/200").into(binding.imageHttpDuck)
            }
            else{
                Picasso.get().load("https://random-d.uk/api/http/404").into(binding.imageHttpDuck)
            }
        }

        model.errorMessage.observe(this){
            if (it != null){
                binding.tvError.text = it
                binding.tvError.visibility = View.VISIBLE
                binding.tvInfo.visibility = View.GONE
                binding.tvCountimg.visibility = View.GONE
                binding.tvCountgif.visibility = View.GONE

            }else{
                binding.tvError.visibility = View.GONE
                binding.tvInfo.visibility = View.VISIBLE
                binding.tvCountimg.visibility = View.VISIBLE
                binding.tvCountgif.visibility = View.VISIBLE
            }
        }

    }

    private val DUCK_RANDOM = 25
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, DUCK_RANDOM, 0, "RandomDuck");

        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean{
        if(item.itemId == DUCK_RANDOM){
            startActivity(Intent(this, DuckActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
    }


}
