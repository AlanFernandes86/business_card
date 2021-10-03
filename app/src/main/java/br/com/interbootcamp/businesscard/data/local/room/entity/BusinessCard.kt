package br.com.interbootcamp.businesscard.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String = "",
    var phone: String = "",
    var email: String = "",
    var company: String = "",
    var backgroundColor: Int = 0
)
