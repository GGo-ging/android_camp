package com.example.mbtitest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager : ViewPager2

    val questionnaireResults = QuestionnaireResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false // 터치로 넘기는 기능 false

    }

    fun moveToNextQuestion() {
        if(viewPager.currentItem==3) {

            // 마지막 페이지 -> 결과 화면으로 이동
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(questionnaireResults.results))
            startActivity(intent)

        } else {
            val nextItem = viewPager.currentItem + 1 // currentItem = 현재페이지
            if(nextItem < (viewPager.adapter?.itemCount ?: 0)) {
                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }

}

class QuestionnaireResults {
    val results = mutableListOf<Int>()

    fun addResponses(responses: List<Int>) { // 각 페이지의 결과값을 저장함 ex) 1,1,2
        val mostFrequent = responses.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
        // 각 숫자를 grouping(모아줌) 후 가장 많은 것을 선택함 ex) 1 - 2개, 2 - 1개 그러므로 1
        mostFrequent?.let { results.add(it) }
    }
}