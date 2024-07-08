package com.example.footballfixtures

import java.io.InputStreamReader

object Helper {
    fun readFileResource(filename:String): String  {
        val inputSteam=Helper::class.java.getResourceAsStream(filename)
        val builder=StringBuilder()
        val reader= InputStreamReader(inputSteam, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()

    }
}