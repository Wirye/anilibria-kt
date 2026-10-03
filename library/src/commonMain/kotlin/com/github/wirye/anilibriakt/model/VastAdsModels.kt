package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VastAd(
    val id: String,
    val url: String,
    @SerialName("ad_erid") val adErid: String? = null,
    @SerialName("ad_company_itn") val adCompanyItn: Long? = null,
    @SerialName("ad_company_name") val adCompanyName: String? = null
)