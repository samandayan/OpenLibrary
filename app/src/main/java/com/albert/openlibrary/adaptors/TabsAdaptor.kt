package com.albert.openlibrary.adaptors

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import com.albert.openlibrary.fragments.SearchFragment
import com.albert.openlibrary.fragments.WishList

/**
 * This class is the adaptor that connects the
 * viewPager with the tab, and displays the
 * corresponding fragment based off of the
 * currently selected tab.
 */
class TabsAdaptor(var context: Context, fm: FragmentManager, var totalTabs: Int) : FragmentPagerAdapter(fm) {


    /**
     * This method returns the required fragment
     * for each tab to display corresponding data
     * to the user.
     */
    override fun getItem(position: Int): Fragment? {
        when(position) {
             0 -> return SearchFragment()
             1 -> return WishList()
            else -> return null
        }
        return null
    }

    /**
     * The total number of tabs
     * is returned from here.
     */
    override fun getCount(): Int {
        return totalTabs
    }
}