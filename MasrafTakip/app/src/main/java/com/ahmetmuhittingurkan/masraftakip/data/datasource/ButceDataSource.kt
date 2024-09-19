package com.ahmetmuhittingurkan.masraftakip.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity
import com.ahmetmuhittingurkan.masraftakip.room.GelirDAO
import com.ahmetmuhittingurkan.masraftakip.room.GiderDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ButceDataSource(var gelirDAO: GelirDAO,var giderDAO: GiderDAO) {

    suspend fun kayitGelir(gelir:GelirEntity) {
        gelirDAO.kayitGelir(gelir)
    }

    suspend fun kayitGider(gider:GiderEntity){
        giderDAO.kayitGider(gider)
    }

    suspend fun silGelir(gelir:GelirEntity) {
        gelirDAO.silGelir(gelir)
    }

    suspend fun silGider(gider:GiderEntity) {
        giderDAO.silGider(gider)
    }

    fun getAllGelir(): LiveData<List<GelirEntity>> {
        return gelirDAO.getAllGelir()
    }

    fun getAllGider(): LiveData<List<GiderEntity>> {
        return giderDAO.getAllGider()
    }
}