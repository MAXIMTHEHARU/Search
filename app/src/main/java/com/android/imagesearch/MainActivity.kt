package com.android.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.android.imagesearch.databinding.ActivityMainBinding
import com.android.imagesearch.feature.bookmark.BookmarkFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    //공유 저장소 
    var likedItems : ArrayList<SearchItemModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnSearchFragment.setOnClickListener {
                setFragment(SearchFragment())
            }
            btnBookmarkFragment.setOnClickListener {
                setFragment(BookmarkFragment())
            }
            setFragment(SearchFragment())
        }
    }
    private fun setFragment(frag : Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }
    fun addLikedItem(item: SearchItemModel) {
        if (!likedItems.contains(item)) {
            likedItems.add(item)
        }
    }
    fun removeLikedItem(item: SearchItemModel) {
        likedItems.remove(item)
    }
}