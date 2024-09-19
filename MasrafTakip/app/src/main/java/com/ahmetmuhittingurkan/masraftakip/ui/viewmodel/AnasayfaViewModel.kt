package com.ahmetmuhittingurkan.masraftakip.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ahmetmuhittingurkan.masraftakip.data.entity.ButceItem
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity
import com.ahmetmuhittingurkan.masraftakip.data.repository.ButceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var brepo:ButceRepository): ViewModel(){

    val gelirList: LiveData<List<GelirEntity>> = brepo.getAllGelir()
    val giderList: LiveData<List<GiderEntity>> = brepo.getAllGider()

    fun getButceItems(): LiveData<List<ButceItem>> {
        val gelirList = brepo.getAllGelir()
        val giderList = brepo.getAllGider()

        return MediatorLiveData<List<ButceItem>>().apply {
            addSource(gelirList) { gelirler->
                val gelirItems = gelirler.map { ButceItem.GelirItem(it) }
                val giderItems = giderList.value?.map { ButceItem.GiderItem(it) } ?: emptyList()
                value = gelirItems + giderItems
            }
            addSource(giderList) { giderler ->
                val giderItems = giderler.map { ButceItem.GiderItem(it) }
                val gelirItems = gelirList.value?.map { ButceItem.GelirItem(it) } ?: emptyList()
                value = gelirItems + giderItems
            }
        }
    }


    fun silGider(gider: GiderEntity) {
        viewModelScope.launch {
            brepo.silGider(gider)
        }
    }

    fun silGelir(gelir: GelirEntity) {
        viewModelScope.launch {
            brepo.silGelir(gelir)
        }
    }

    fun getToplamGelir(): LiveData<Double> {
        return brepo.getAllGelir().map { gelirList ->
            gelirList.sumOf { it.miktar }
        }
    }

    fun getToplamGider(): LiveData<Double> {
        return brepo.getAllGider().map { giderList ->
            giderList.sumOf { it.miktar }
        }
    }

    fun getKalan(): LiveData<Double> {
        val toplamGelir = getToplamGelir()
        val toplamGider = getToplamGider()

        return MediatorLiveData<Double>().apply {
            addSource(toplamGelir) { gelir ->
                val gider = toplamGider.value ?: 0.0
                value = gelir - gider
            }
            addSource(toplamGider) { gider ->
                val gelir = toplamGelir.value ?: 0.0
                value = gelir - gider
            }
        }
    }
}


