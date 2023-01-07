package com.vijay.ottinfoprovider.models

import com.google.gson.annotations.SerializedName

data class PricingInfo(
  @SerializedName("duration")
  val duration: String,
  @SerializedName("screensInfo")
  val screensInfoList: List<ScreenInfo>
)

data class ScreenInfo(
  @SerializedName("numberOfScreens")
  val numberOfScreens: String,
  @SerializedName("price")
  val price: String,
  @SerializedName("defaultTransferPrice")
  val defaultTransferPrice: String,
  @SerializedName("offeredPrice")
  val offeredPrice: String,
)