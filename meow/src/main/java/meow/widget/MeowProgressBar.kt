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

package meow.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import meow.controller
import meow.widget.impl.ProgressBarInterface

/**
 * Meow Progress Bar class.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-03-23
 */

@Suppress("unused")
class MeowProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ProgressBar(context, attrs, defStyleAttrs), ProgressBarInterface {

    override fun show() {
        visibility = View.VISIBLE
    }

    override fun hide() {
        visibility = View.GONE
    }

    override fun setIndeterminateProgress(indeterminate: Boolean) {
        isIndeterminate = indeterminate
    }

    override fun setIndeterminateTintList(tint: ColorStateList?) {
        if (tint != null)
            super.setIndeterminateTintList(controller.onColorStateListGet(tint))
    }

    override fun setProgressBackgroundTintList(tint: ColorStateList?) {
        if (tint != null)
            super.setProgressBackgroundTintList(controller.onColorStateListGet(tint))
    }
}