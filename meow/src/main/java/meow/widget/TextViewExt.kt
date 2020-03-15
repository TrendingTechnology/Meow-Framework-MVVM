package meow.widget

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.widget.TextView
import androidx.databinding.BindingAdapter
import meow.controller

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

/**
 * [android.widget.TextView] Extensions.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-03-15
 */

@SuppressLint("SetTextI18n")
@BindingAdapter("android:text", "isPersian", requireAll = false)
fun setText(view: TextView, text: String, isPersian: Boolean) {
    view.text = "$isPersian $text"
}

@BindingAdapter("android:textColor")
fun setTextColor(view: TextView, textColor: ColorStateList) {
    view.setTextColor(controller.onColorStateListGet(textColor))
}

@BindingAdapter("android:textColor")
fun setTextColor(view: TextView, textColor: Int) {
    view.setTextColor(controller.onColorGet(textColor))
}