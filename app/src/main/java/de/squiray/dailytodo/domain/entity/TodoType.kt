package de.squiray.dailytodo.domain.entity

import de.squiray.dailytodo.R

enum class TodoType(val type: Int) {
    DAILY_TO_DO(R.string.nav_title_daily_todo),

    FEAR(R.string.nav_title_fear),

    PASSION(R.string.nav_title_passion)
}
