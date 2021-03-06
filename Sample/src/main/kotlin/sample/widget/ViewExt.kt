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

package sample.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.ImagesPlugin
import io.noties.markwon.syntax.Prism4jThemeDarkula
import io.noties.markwon.syntax.Prism4jThemeDefault
import io.noties.markwon.syntax.SyntaxHighlightPlugin
import io.noties.prism4j.GrammarLocator
import io.noties.prism4j.Prism4j
import io.noties.prism4j.Prism4j.Grammar
import meow.controller
import meow.ktx.javaClass
import sample.prism4j.languages.*

/**
 * View Extensions.
 *
 * @author  Hamidreza Etebarian
 * @version 1.0.0
 * @since   2020-04-14
 */

fun Context.createMarkwon(): Markwon {
    return Markwon.builder(this).apply {
        val prism4j = Prism4j(MyGrammarLocator())
        val theme =
            if (controller.isNightMode) Prism4jThemeDarkula.create() else Prism4jThemeDefault.create()
        val highlight = SyntaxHighlightPlugin.create(prism4j, theme)
        usePlugin(highlight)

        usePlugin(ImagesPlugin.create())
        usePlugin(HtmlPlugin.create())
        usePlugin(TablePlugin.create(this@createMarkwon))
    }.build()
}

class MyGrammarLocator : GrammarLocator {
    override fun grammar(
        prism4j: Prism4j,
        language: String
    ): Grammar? {
        return when (language) {
            "clike" -> Prism_clike.create(prism4j)
            "groovy", "properties" -> Prism_groovy.create(prism4j)
            "json" -> Prism_json.create(prism4j)
            "kotlin" -> Prism_kotlin.create(prism4j)
            "markup", "xml" -> Prism_markup.create(prism4j)
            else -> null
        }
    }

    override fun languages() =
        setOf("clike", "groovy", "json", "kotlin", "markup", "properties", "xml")
}

fun TextView.setMarkdownData(markdownData: Spanned) {
    movementMethod = LinkMovementMethod.getInstance()
    context.createMarkwon().setParsedMarkdown(this, markdownData)
}

fun String.githubRaw() =
    "https://raw.githubusercontent.com/oneHamidreza/Meow-Framework-MVVM/master/$this"

inline fun <reified T> Activity.startActivity(finish: Boolean = false) {
    startActivity(Intent(this, javaClass<T>()))
    if (finish)
        finish()
}