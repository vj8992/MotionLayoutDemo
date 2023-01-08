package com.vijay.ottinfoprovider.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vijay.ottinfoprovider.R
import com.vijay.ottinfoprovider.databinding.ActivityHomeBinding
import com.vijay.ottinfoprovider.models.OttCardInfoModel

class HomeActivity : AppCompatActivity() {

  private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
    ActivityHomeBinding.inflate(layoutInflater)
  }
  private lateinit var viewModel: OttViewModel
  private var selectedDuration = "1 month"
  private var selectedScreen = "1 screen"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(viewBinding.root)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = "Manage Ott Pack"
    viewModel = ViewModelProvider(this)[OttViewModel::class.java]
    viewModel.getStream().observe(this, Observer {
      bindData(it)
    })
    setListeners()
  }

  private fun setListeners(){
    viewBinding.buttonGroup.addOnButtonCheckedListener { _, checkedId, _ ->
      when(checkedId){
        R.id.oneMonthBTN ->{
          selectedDuration = "1 month"
        }
        R.id.sixMonthBTN ->{
          selectedDuration = "6 months"
        }
        R.id.oneYearBTN ->{
          selectedDuration = "12 months"
        }
      }
      viewModel.applyScreenFilter(selectedScreen, selectedDuration)
    }

    viewBinding.screenInfoRG.setOnCheckedChangeListener { _, checkedId ->
      when(checkedId){
        R.id.screenOneRB ->{
          selectedScreen = "1 screen"
        }
        R.id.screenTwoRB ->{
          selectedScreen = "2 screen"
        }
      }
      viewModel.applyScreenFilter(selectedScreen, selectedDuration)
    }

    viewBinding.motionLayout.setTransitionListener(object: TransitionListener{
      override fun onTransitionStarted(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
      ) {
      }

      override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float,
      ) {
      }

      override fun onTransitionCompleted(
        motionLayout: MotionLayout?,
        currentId: Int,
      ) {
        when(currentId){
          R.id.swipeLeftComplete,
          R.id.swipeRightComplete ->{
            viewBinding.motionLayout.progress = 0f
            viewBinding.motionLayout.setTransition(R.id.start, R.id.end)
            viewModel.swipe()
          }
        }
      }

      override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float,
      ) {
      }
    })
  }

  private fun bindData(model: OttCardInfoModel){
    viewBinding.nameTV.text = model.cardTop.packageName
    viewBinding.providersTV.text = "${model.cardTop.providersList.size.toString()} Providers"
    viewBinding.calculatedPriceTV.text = model.cardTop.calculatedPrice.toString()
    viewBinding.billingCycleTV.text = "Billed annually"
  }
}