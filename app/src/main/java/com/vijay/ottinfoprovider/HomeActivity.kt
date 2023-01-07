package com.vijay.ottinfoprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.vijay.ottinfoprovider.databinding.ActivityHomeBinding
import com.vijay.ottinfoprovider.repo.OttApiInterface
import com.vijay.ottinfoprovider.repo.RepoHelper

class HomeActivity : AppCompatActivity() {

  private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
    ActivityHomeBinding.inflate(layoutInflater)
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(viewBinding.root)
    lifecycleScope.launchWhenCreated {
      val api = RepoHelper.getInstance().create(OttApiInterface::class.java)
      val result = api.getPackages()
    }
  }
}