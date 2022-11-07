package com.example.stellantiscarapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_table")
class Car(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "car_name")
    var carName: String = "",

    @ColumnInfo(name = "manufacturer_name")
    var manufacturerName: String = "",

    @ColumnInfo(name = "last_maintenance")
    var lastMaintenance: String = "",

    )