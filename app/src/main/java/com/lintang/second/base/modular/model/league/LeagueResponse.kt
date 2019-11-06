package com.lintang.second.base.modular.model.league

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("leagues") var leagues: List<SingleLeague>? = null
)



