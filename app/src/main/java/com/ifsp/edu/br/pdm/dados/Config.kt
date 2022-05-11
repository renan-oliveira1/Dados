package com.ifsp.edu.br.pdm.dados

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Config(val numberDices: Int = 1, val numberFaces: Int = 6):Parcelable
