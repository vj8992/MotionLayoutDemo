package com.vijay.ottinfoprovider.models

import com.google.gson.annotations.SerializedName

class GetListResponse<T> {
  @SerializedName("data")
  val data: List<T>? = null
}