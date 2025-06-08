package com.example.fitnessflowapp.static

import android.content.Context
import com.example.fitnessflowapp.R
import com.example.fitnessflowapp.data.model.SetupPage

//potom pridat ine descriptions co sa tu hodia
//koment ok
/**
 * poskytuje zoznam uvodnych obrazoviek pri prvom spusteni aplikacie
 * obsahuje titulok a popis nacitany zo strings.xml
 */
object SetupPages {

    /**
     * vrati zoznam setup obrazoviek s obsahom
     *
     * @param context nacitanie stringov zo suboru resources
     * @return obsah ktory sa zobrazi pocas nastavenia profilu
     */
    fun getPages(context: Context): List<SetupPage> = listOf(
        SetupPage(
            title = context.getString(R.string.setup_title_1),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_2),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_3),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_4),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_5),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_6),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_7),
            description = context.getString(R.string.setup_lorem)
        ),
        SetupPage(
            title = context.getString(R.string.setup_title_8),
            description = context.getString(R.string.setup_lorem)
        )

    )
}