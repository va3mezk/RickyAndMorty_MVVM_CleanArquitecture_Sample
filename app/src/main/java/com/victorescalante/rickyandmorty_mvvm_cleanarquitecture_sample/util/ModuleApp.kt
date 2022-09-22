package com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.util

import android.content.Context
import androidx.room.Room
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.repository.CharacterRepositoryImpl
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.CharacterDao
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.CharacterDataBase
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.remote.ApiService
import com.victorescalante.rickyandmorty_mvvm_cleanarquitecture_sample.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Singleton
    @Provides
    fun retrofitProvider(): ApiService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CharacterDataBase::class.java, Constants.DATA_BASE).build()

    @Singleton
    @Provides
    fun providesDao(db: CharacterDataBase) = db.providesData()

    @Singleton
    @Provides
    fun providesRepository(api: ApiService, room: CharacterDao): CharacterRepository =
        CharacterRepositoryImpl(api, room)


}