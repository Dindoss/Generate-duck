package com.ange.tp_rendu_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class ListViewModel : ViewModel(){

    val data = MutableLiveData<ListBean?>()
    val errorMessage = MutableLiveData<String?>()

    fun loadData(){
        data.postValue(null)
        errorMessage.postValue(null)

        thread {
            try{
                data.postValue(RequestUtils.loadList())

            }catch (e : Exception){
                e.printStackTrace()
                errorMessage.postValue("Erreur " + e.message)
            }
        }
    }

}