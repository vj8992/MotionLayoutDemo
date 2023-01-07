package com.vijay.ottinfoprovider.models

import com.google.gson.annotations.SerializedName

data class ProvidersInfo(
  @SerializedName("id")
  val id: String,
  @SerializedName("providerName")
  val providerName: String,
  @SerializedName("providerTier")
  val providerTier: String,
  @SerializedName("concurrentViews")
  val concurrentViews: Int,
  @SerializedName("offeredPrice")
  val offeredPrice: Int,
  @SerializedName("providerEmail")
  val providerEmail: String,
  @SerializedName("providerPhone")
  val providerPhone: String,
  @SerializedName("logoPath")
  val logoPath: String,
)
