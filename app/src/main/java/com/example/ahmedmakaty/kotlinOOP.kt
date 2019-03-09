package com.example.ahmedmakaty

class KotlinOOP(var name: String, var count: Int) {

    init {
        require(count>0) {"Count can not be less that 1"}
    }

}