package com.ahmetmuhittingurkan.masraftakip.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity
import com.ahmetmuhittingurkan.masraftakip.data.repository.ButceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiderViewModel @Inject constructor(var brepo:ButceRepository): ViewModel() {

    fun kayitGider(gider:GiderEntity) {
        viewModelScope.launch {
            brepo.kayitGider(gider)
        }
    }


}