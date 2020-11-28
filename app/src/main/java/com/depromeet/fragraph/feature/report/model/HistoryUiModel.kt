package com.depromeet.fragraph.feature.report.model

import com.depromeet.fragraph.domain.model.Memo
import com.depromeet.fragraph.domain.model.enums.IncenseTitle

data class HistoryUiModel(
    val id: Int,
    val createdAt: Long,
    val playTime: String,
    val incenseTitle: IncenseTitle,
    val memo: Memo,
    val keywordFirst: String,
    val keywordSecond: String,
    val keywordThird: String,
    val isExisted: Boolean,
    var isBack: Boolean,
)

fun getEmptyUiModel(createdAt: Long) = HistoryUiModel(
    0, createdAt, "0", IncenseTitle.LAVENDER, Memo(0, "", "",null), "", "", "", isExisted = true, isBack = false
)