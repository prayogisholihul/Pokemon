package com.zogik.core.di

import android.content.Context
import androidx.room.Room
import com.zogik.core.data.detail.data_source.local.dao.DetailDao
import com.zogik.core.data.my_pokemon.data_source.local.dao.MyPokemonDao
import com.zogik.core.database.AppDb
import com.zogik.core.util.Constant.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext appContext: Context): AppDb {
        return Room.databaseBuilder(appContext, AppDb::class.java, DB_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Provides
    fun provideDetailDao(appDatabase: AppDb): DetailDao {
        return appDatabase.detailDao()
    }

    @Provides
    fun provideMyPokemonDao(appDatabase: AppDb): MyPokemonDao {
        return appDatabase.myPokemonDao()
    }
}
