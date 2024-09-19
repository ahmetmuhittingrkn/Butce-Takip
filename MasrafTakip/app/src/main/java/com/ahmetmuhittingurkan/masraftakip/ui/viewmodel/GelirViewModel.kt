package com.ahmetmuhittingurkan.masraftakip.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.repository.ButceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GelirViewModel @Inject constructor(var brepo:ButceRepository): ViewModel() {

    fun kayitGelir(gelir:GelirEntity) {
        viewModelScope.launch {
            brepo.kayitGelir(gelir)
        }
    }

}


