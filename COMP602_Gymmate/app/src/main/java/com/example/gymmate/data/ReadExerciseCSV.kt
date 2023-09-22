package com.example.gymmate.data

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ReadExerciseCSV (private val inputStream: InputStream){
    fun read(): List<List<String>> {
        val resultList: MutableList<List<String>> = ArrayList()
        val reader = BufferedReader(InputStreamReader(inputStream))
        try {
            var csvLine: String?
            while (reader.readLine().also { csvLine = it } != null) {
                val row: List<String> = csvLine!!.split(",").map { it.trim() }
                resultList.add(row)
            }
        } catch (e: IOException) {
            Log.e("CSVFile", "Error reading CSV file", e)
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                Log.e("CSVFile", "Error closing input stream", e)
            }
        }
        return resultList
    }
}