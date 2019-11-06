package com.lintang.second.base.modular.model.match

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(@SerializedName("event") var events: List<SingleMatch>? = null)