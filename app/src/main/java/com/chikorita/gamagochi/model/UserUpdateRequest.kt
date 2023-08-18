package com.chikorita.gamagochi.model

data class UserUpdateRequest(
    val newMajor : String = "ARTIFICIAL_INTELIGENCE",
    val newUserName : String = "string",
    val userId : Int = 0,
)

