package com.example.ahmedmakaty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val API_KEY = "api_key"
        var name: String?
        var loading = false

        var array: MutableList<String?> = MutableList<String?>(5,{x-> "hi"})

        var map: HashMap<String, String> = HashMap()

        map.put("hi", "hello")


        array[3] = "mohamed"

        var cleanArray = array.filter { it != null }

        array.forEach { println(it) }

        var myObject: KotlinOOP = KotlinOOP("ahmed", 5)

        var list1to10 = 1..10

        val mul = whatever(5)

        var function = { num: Int, num1: Int -> num + num1 }

        Log.d("valFunction", function(2, 3).toString())
        //Log.d("WHATEVER", mul(2).toString())
    }

    fun getSum(num1: Int, num2: Int) = num1 + num2

    fun getSumUnknownNumber(vararg nums: Int): Int {
        var sum = 0

        nums.forEach { sum += it }

        return sum
    }

    fun factorial(num: Int): Int {
        tailrec fun fact(num: Int, res: Int): Int {
            if (num == 1) return res
            else return fact(num - 1, res * num)
        }
        return fact(num, 1)
    }

    fun whatever(num: Int): (Int) -> Int = { num2 -> num * num2 }
}
