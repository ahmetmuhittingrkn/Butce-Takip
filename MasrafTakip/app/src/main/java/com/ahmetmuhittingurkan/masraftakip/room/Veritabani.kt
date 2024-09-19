package com.ahmetmuhittingurkan.masraftakip.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity

@Database(entities =[GelirEntity::class, GiderEntity::class], version = 1,exportSchema = false)
abstract class Veritabani : RoomDatabase(){

    abstract fun getGelirDAO() : GelirDAO
    abstract fun getGiderDAO() : GiderDAO

}