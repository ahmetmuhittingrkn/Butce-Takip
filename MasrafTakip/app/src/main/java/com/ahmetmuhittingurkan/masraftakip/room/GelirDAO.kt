package com.ahmetmuhittingurkan.masraftakip.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity

@Dao
interface GelirDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun kayitGelir(gelir: GelirEntity)

    @Delete
    suspend fun silGelir(gelir: GelirEntity)

    @Query("SELECT * FROM gelir_table ORDER BY id ASC")
    fun getAllGelir(): LiveData<List<GelirEntity>>
}