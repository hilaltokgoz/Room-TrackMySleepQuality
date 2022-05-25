/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDatabaseDao{
    @Insert
    fun insert(night: SleepNight){}
    @Update
    fun update(night: SleepNight){}
    @Query(value = ("SELECT * from daily_sleep_quality_table WHERE nightId = :key"))//fun daki argumana erişim için : kullanılır.
    fun get(key:Long):SleepNight? //? null yapılabilir.
    @Query("DELETE from daily_sleep_quality_table")
    fun clear() //Tablonun kendisini değil, içindekileri siler.
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1") //id'yi büyükten küçüğe sıralar ve Limit 1 olduğu için tek bir öğe gösterir.
    fun getTonight(): SleepNight?
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")//id'si azalan sırada sıralanmış tüm  id'leri gösterir.
    fun getAllNights(): LiveData<List<SleepNight>>//getAllNights(), LiveData olarak SleepNight varlıklarının bir listesini döndürsün. Room, bu LiveData'yı sizin için güncel tutar;



}
