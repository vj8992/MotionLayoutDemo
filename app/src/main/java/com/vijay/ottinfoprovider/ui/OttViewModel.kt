package com.vijay.ottinfoprovider.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vijay.ottinfoprovider.models.OttCardInfoModel
import com.vijay.ottinfoprovider.models.OttPackageInfo
import com.vijay.ottinfoprovider.repo.OttApiInterface
import com.vijay.ottinfoprovider.repo.RepoHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OttViewModel: ViewModel() {

  private var ottPackageList = MutableLiveData<List<OttPackageInfo>>()
  private var filteredList = mutableListOf<OttPackageInfo>()

  private val stream = MutableLiveData<OttCardInfoModel>()

  private fun getOttPackages(){
    viewModelScope.launch {
      val api = RepoHelper.getInstance().create(OttApiInterface::class.java)
      val result = api.getPackages()
      withContext(Dispatchers.Main){
        ottPackageList.value = result.data
        applyScreenFilter("1 screen", "1 month")
      }
    }
  }

  fun applyScreenFilter(screenSize: String, duration: String){
    val list = ottPackageList.value
    filteredList.clear()
    list?.forEach { ottPackageInfo ->
      ottPackageInfo.pricingList.forEach { pricingInfo ->
        if (pricingInfo.duration == duration){
          pricingInfo.screensInfoList.forEach {
            if (it.numberOfScreens == screenSize){
              if (!filteredList.contains(ottPackageInfo))
                filteredList.add(ottPackageInfo)
            }
          }
        }
      }
    }
    updateCards()
  }

  private var currentIndex = 0

  private val topCard
    get() = filteredList[currentIndex % filteredList.size]
  private val bottomCard
    get() = filteredList[(currentIndex + 1) % filteredList.size]

  init {
    getOttPackages()
  }

  fun swipe() {
    currentIndex += 1
    updateCards()
  }

  private fun updateCards() {
    stream.value = OttCardInfoModel(
      cardTop = topCard,
      cardBottom = bottomCard
    )
  }

  fun getStream(): LiveData<OttCardInfoModel>{
    return stream
  }
}