package com.pss.baeghayan_android.view

import androidx.activity.viewModels
import com.pss.baeghayan_android.R
import com.pss.baeghayan_android.base.BaseActivity
import com.pss.baeghayan_android.databinding.ActivityMainBinding
import com.pss.baeghayan_android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()


    override fun init() {

    }
}