package com.packmart.app.data.repository

import com.packmart.app.data.model.ActionState
import com.packmart.app.data.model.Masak
import com.packmart.app.data.remote.MasakService
import com.packmart.app.data.remote.RetrofitApi
import retrofit2.await
import java.lang.Exception

class MasakRepository {

    private val masakService: MasakService by lazy { RetrofitApi.masakService() }

    suspend fun listMasak() : ActionState<List<Masak>> {
        return try {
            val list = masakService.listMasak().await()
            ActionState(list.data)
        } catch (e : Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun detailMasak(url: String) : ActionState<Masak> {
        return try {
            val list = masakService.detailMasak(url).await()
            ActionState(list.data.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchMasak(query: String) : ActionState<List<Masak>> {
        return try {
            val list = masakService.searchMasak(query).await()
            ActionState(list.data)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

}