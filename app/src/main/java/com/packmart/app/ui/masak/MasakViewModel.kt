package com.packmart.app.ui.masak

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packmart.app.data.model.ActionState
import com.packmart.app.data.model.Masak
import com.packmart.app.data.repository.MasakRepository
import kotlinx.coroutines.launch

class MasakViewModel : ViewModel() {
   private val repo: MasakRepository by lazy { MasakRepository() }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<Masak>>()
    val detailResp = MutableLiveData<Masak>()
    val searchResp = MutableLiveData<List<Masak>>()

    val url = MutableLiveData("")
    val query = MutableLiveData("")

    fun listMasak() {
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listMasak()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun detailMasak(url: String? = this.url.value){
        url?.let{
            viewModelScope.launch {
                loading.value = true
                val resp = repo.detailMasak(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value = false
            }
        }
    }

 fun searchMasak(query: String? = this.query.value){
     query?.let{
         viewModelScope.launch {
             loading.value = true
             val resp = repo.searchMasak(it)
             actionState.value = resp
             searchResp.value = resp.data
             loading.value = false
         }
     }
 }

}