package com.vijay.ottinfoprovider.models

import com.google.gson.annotations.SerializedName

data class OttPackageInfo(
  @SerializedName("id")
  val id: String,
  @SerializedName("packageName")
  val packageName: String,
  @SerializedName("calculatedPrice")
  val calculatedPrice: Float,
  @SerializedName("sellingPrice")
  val sellingPrice: Float,
  @SerializedName("defaultTransferPrice")
  val defaultTransferPrice: Float,
  @SerializedName("providersInfo")
  val providersList: List<ProvidersInfo>,
  @SerializedName("pricingInfo")
  val pricingList: List<PricingInfo>,
)
