package com.ahmetmuhittingurkan.masraftakip.data.entity

sealed class ButceItem {

    data class GelirItem(val gelir: GelirEntity) : ButceItem()
    data class GiderItem(val gider: GiderEntity) : ButceItem()
}