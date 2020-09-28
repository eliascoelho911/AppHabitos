package br.com.eliascoelho911.habitos.util

import android.content.Context
import androidx.annotation.StringRes
import org.koin.java.KoinJavaComponent.inject

private val context: Context by inject(Context::class.java)

fun getString(@StringRes res: Int) = context.getString(res)