package com.ange.tp_rendu_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ange.tp_rendu_kotlin.databinding.ActivityRandomBinding
import com.squareup.picasso.Picasso

class DuckActivity : AppCompatActivity(), View.OnClickListener {

    // Déclaration du binding contenant les références des composants
    val binding by lazy { ActivityRandomBinding.inflate(layoutInflater) }

    //Acces à mes données
    val model by lazy { ViewModelProvider(this).get(DuckViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Affichage de l'interface graphique
        setContentView(binding.root)

        binding.btReload.setOnClickListener(this)

        model.data.observe(this){
            if(it != null && !it.url.isNullOrEmpty()) {
                Picasso.get().load("${it.url}").into(binding.imageViewDuck)
            }
        }

        model.progressBar.observe(this){
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        model.errorMessage.observe(this){
            if (it != null){
                binding.tvReload.visibility = View.GONE
                binding.imageViewDuck.visibility = View.GONE
                binding.tvEreload.text = it
                binding.tvEreload.visibility = View.VISIBLE

            }else{
                binding.tvEreload.visibility = View.GONE
                binding.tvReload.visibility = View.VISIBLE
                binding.imageViewDuck.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(v: View?) {
        showDuck()
    }

    fun showDuck(){
        model.loadData()
    }
}
