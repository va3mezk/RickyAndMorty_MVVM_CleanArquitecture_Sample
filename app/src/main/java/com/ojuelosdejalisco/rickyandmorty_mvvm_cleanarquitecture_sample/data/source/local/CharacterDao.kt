package com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ojuelosdejalisco.rickyandmorty_mvvm_cleanarquitecture_sample.data.source.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun characterInsert(data: List<CharacterEntity>)

    @Query("delete from character")
    suspend fun characterDelete()

    @Query("select * from character")
    suspend fun characterSelect(): List<CharacterEntity>

}








