package com.example.mbtitest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {

        // 질문지 프라그먼트

        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(position)
    }
}