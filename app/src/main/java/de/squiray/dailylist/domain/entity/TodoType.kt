package de.squiray.dailylist.domain.entity

import de.squiray.dailylist.R

enum class TodoType(val type: Int) {
    DAILY_TO_DO(R.string.nav_title_daily_todo),

    FEAR(R.string.nav_title_fear),

    GOAL(R.string.nav_title_goal)
}
