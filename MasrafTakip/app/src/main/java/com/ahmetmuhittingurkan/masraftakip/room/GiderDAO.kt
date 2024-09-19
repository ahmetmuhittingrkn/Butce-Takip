package com.ahmetmuhittingurkan.masraftakip.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmetmuhittingurkan.masraftakip.data.entity.GelirEntity
import com.ahmetmuhittingurkan.masraftakip.data.entity.GiderEntity

@Dao
interface GiderDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun kayitGider(gider:GiderEntity)

    @Delete
    suspend fun silGider(gider: GiderEntity)

    @Query("SELECT * FROM gider_table ORDER BY id ASC")
    fun getAllGider(): LiveData<List<GiderEntity>>
}


