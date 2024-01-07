package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.local.entity.Meigen

@Dao
interface MeigenDao {
    @Insert
    fun insertAll(vararg maigens: Meigen)
}
