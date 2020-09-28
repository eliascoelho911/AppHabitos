package br.com.eliascoelho911.habitos.util

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import org.koin.java.KoinJavaComponent.inject

private val context: Context by inject(Context::class.java)

fun getString(@StringRes res: Int) = context.getString(res)
fun getColor(@ColorRes res: Int) = ContextCompat.getColor(context, res)
fun getDrawable(@DrawableRes res: Int) = ContextCompat.getDrawable(context, res)