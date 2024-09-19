package com.ahmetmuhittingurkan.masraftakip.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("gelir_table")
data class GelirEntity(@PrimaryKey(autoGenerate = true) var id:Int=0,
                       var miktar:Double,
                       var bilgi:String,
                       var tarih:String) : Serializable {
}