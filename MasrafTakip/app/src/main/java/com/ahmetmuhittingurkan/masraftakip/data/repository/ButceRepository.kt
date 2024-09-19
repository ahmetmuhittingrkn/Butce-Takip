package com.ahmetmuhittingurkan.masraftakip.data.repository

import androidx.lifecycle.LiveData
import com.ahmetmuhittingurkan.masraftakip.data.datasource.ButceDataSource
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity

class ButceRepository(var bds:ButceDataSource) {

    suspend fun kayitGelir(gelir: GelirEntity) = bds.kayitGelir(gelir)

    suspend fun kayitGider(gider:GiderEntity) = bds.kayitGider(gider)

    suspend fun silGelir(gelir: GelirEntity) = bds.silGelir(gelir)

    suspend fun silGider(gider:GiderEntity) = bds.silGider(gider)

    fun getAllGelir(): LiveData<List<GelirEntity>> = bds.getAllGelir()

    fun getAllGider(): LiveData<List<GiderEntity>> = bds.getAllGider()
}