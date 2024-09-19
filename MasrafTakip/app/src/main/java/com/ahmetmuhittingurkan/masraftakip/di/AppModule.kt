package com.ahmetmuhittingurkan.masraftakip.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ahmetmuhittingurkan.masraftakip.data.datasource.ButceDataSource
import com.ahmetmuhittingurkan.masraftakip.data.repository.ButceRepository
import com.ahmetmuhittingurkan.masraftakip.room.GelirDAO
import com.ahmetmuhittingurkan.masraftakip.room.GiderDAO
import com.ahmetmuhittingurkan.masraftakip.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): Veritabani {
        return Room.databaseBuilder(
            app.applicationContext,
            Veritabani::class.java,
            "butce_veritabani"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGelirDao(veritabani: Veritabani): GelirDAO {
        return veritabani.getGelirDAO()
    }

    @Provides
    @Singleton
    fun provideGiderDao(veritabani: Veritabani): GiderDAO {
        return veritabani.getGiderDAO()
    }

    @Provides
    @Singleton
    fun provideButceDataSource(gelirDAO: GelirDAO, giderDAO: GiderDAO): ButceDataSource {
        return ButceDataSource(gelirDAO, giderDAO)
    }

    @Provides
    @Singleton
    fun provideButceRepository(bds: ButceDataSource): ButceRepository {
        return ButceRepository(bds)
    }
}
