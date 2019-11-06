package com.lintang.second.base.modular.model.match

import com.google.gson.annotations.SerializedName

data class MatchResponse(@SerializedName("events") var events: List<SingleMatch>? = null)