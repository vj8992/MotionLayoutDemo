package com.vijay.ottinfoprovider.repo

import com.vijay.ottinfoprovider.models.GetListResponse
import com.vijay.ottinfoprovider.models.OttPackageInfo
import retrofit2.Response
import retrofit2.http.GET

interface OttApiInterface {
  @GET("acms/packages/getPackagesInfoList")
  suspend fun getPackages(): GetListResponse<OttPackageInfo>

}