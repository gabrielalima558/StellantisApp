package com.example.stellantiscarapp.database

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface CarDao {
    @Insert
    fun insertCar(car: Car)

    @Query("SELECT * FROM car_table ORDER BY car_name COLLATE NOCASE ASC")
    fun allCars(): PagingSource<Int, Car>

    @Query("SELECT * FROM car_table WHERE car_name = :carName")
    fun getCar(carName: String?): Car?

    @Delete
    fun deleteItem(car: Car)

    @Update
    fun updateItem(car: Car)
}