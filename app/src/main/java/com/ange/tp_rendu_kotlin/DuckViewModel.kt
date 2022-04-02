package com.ange.tp_rendu_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class DuckViewModel : ViewModel(){

    val data = MutableLiveData<DuckBean?>()
    val errorMessage = MutableLiveData<String?>()
    val progressBar = MutableLiveData<Boolean>(false)

    fun loadData(){
        data.postValue(null)
        errorMessage.postValue(null)
        progressBar.postValue(true)

        thread {
            try{
                data.postValue(RequestUtils.loadDuck())

            }catch (e : Exception){
                e.printStackTrace()
                errorMessage.postValue("Erreur " + e.message)
            }
            progressBar.postValue(false)
        }
    }

}