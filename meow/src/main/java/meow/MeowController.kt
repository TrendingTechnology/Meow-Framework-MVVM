/*
 * Copyright (C) 2020 Hamidreza Etebarian & Ali Modares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package meow

import android.content.res.ColorStateList
import android.util.LayoutDirection
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentActivity
import meow.core.api.MeowSession
import meow.util.MeowCurrency
import meow.util.getField
import meow.util.isNightModeFromSettings
import meow.util.setField

/**
 * 🐈 This CAT can control configurations and UI properties in one Application. Just trust it.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-03-01
 */

lateinit var controller: MeowController

class MeowController(
    var app: MeowApp,
    val meowSession: MeowSession = MeowSession()
) {

    init {
        controller = this
    }

    var isDebugMode: Boolean = true
    var isLogTagNative: Boolean = true
    var apiSuccessRange: IntRange = 200..200
    var onException: (exception: Exception) -> Unit = {}
    var dpi: Float = 1f
    var layoutDirection: Int = LayoutDirection.INHERIT
    var language: String = ""
    var currency: MeowCurrency = MeowCurrency.USD
    var calendar: Calendar = Calendar.GEORGIAN
    var rootFolderName: String = "meow"
    var onColorGet: (color: Int) -> Int = { color -> color }
    internal var onColorStateListGet: (colorStateList: ColorStateList) -> ColorStateList =
        { color ->
            color.apply {
                val colors = getField<IntArray>("mColors")!!
                colors.forEachIndexed { index, it ->
                    colors[index] = onColorGet(it)
                }
                setField("mDefaultColor", onColorGet(defaultColor))
                setField("mColors", colors)
            }
            ColorStateList.valueOf(onColorGet(color.defaultColor))
        }
    var defaultTypefaceResId: Int = 0
    var toastTypefaceResId: Int = 0
    var isForceFontPadding: Boolean = false
    var isPersian: Boolean = false
    var changeColor: Boolean = false
    var forceNightMode: Boolean = false

    var theme = Theme.UNDEFINED
        get() = if (app.isNightModeFromSettings() || forceNightMode) Theme.NIGHT else Theme.DAY
        set(value) {
            field = value
            val nightMode = when (value) {
                Theme.DAY -> AppCompatDelegate.MODE_NIGHT_NO
                Theme.NIGHT -> AppCompatDelegate.MODE_NIGHT_YES
                Theme.UNDEFINED -> null
            }
            if (nightMode != null)
                AppCompatDelegate.setDefaultNightMode(nightMode)
        }

    val isNightMode
        get() = theme == Theme.NIGHT

    val isRtl
        get() = layoutDirection == LayoutDirection.RTL

    fun swapTheme() {
        theme = if (isNightMode) Theme.DAY else Theme.NIGHT
    }

    fun updateLanguage(activity: FragmentActivity, language: String) {
        this.language = language
        activity.recreate()
    }

    fun bindApp(app: MeowApp) {
        controller.app = app
        dpi = app.resources.displayMetrics.density
    }

    enum class Theme {
        UNDEFINED, DAY, NIGHT
    }

    enum class Calendar {
        GEORGIAN, JALALI
    }


}