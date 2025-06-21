package com.example.pf1.utils

import android.content.Context
import com.example.pf1.R
import com.example.pf1.model.CenterData
import java.io.BufferedReader
import java.io.InputStreamReader

fun loadCentersFromCsv(context: Context): List<CenterData> {
    val centers = mutableListOf<CenterData>()
    val inputStream = context.resources.openRawResource(R.raw.centers)
    val reader = BufferedReader(InputStreamReader(inputStream))

    reader.useLines { lines ->
        lines.drop(1).forEach { line -> // Ignorar la primera línea con los encabezados
            val tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)".toRegex())
            if (tokens.size == 12) {
                centers.add(
                    CenterData(
                        id = tokens[0].toInt(),
                        name = tokens[1],
                        description = tokens[2],
                        address = tokens[3],
                        latitude = tokens[4].toDouble(),
                        longitude = tokens[5].toDouble(),
                        direccion = tokens[6],
                        phone = tokens[7],
                        hours = tokens[8],
                        materialsRecycled = tokens[9],
                        urlGoogle = tokens[10],
                        imageUrl = tokens[11]
                    )
                )
            }
        }
    }

    return centers
}