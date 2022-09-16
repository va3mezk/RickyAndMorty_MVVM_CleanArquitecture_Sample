package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDataBase:RoomDatabase() {
    abstract fun providesData(): CharacterDao
}



