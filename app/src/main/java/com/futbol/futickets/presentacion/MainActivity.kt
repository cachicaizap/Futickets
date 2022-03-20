package com.futbol.futickets.presentacion

import com.futbol.futickets.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import com.futbol.futickets.R
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var lstFragments = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(R.id.itTicket, TicketFragment())
        lstFragments.add(R.id.itTicket)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itTicket -> {
                    changeFragment1(R.id.itTicket, TicketFragment())
                    true
                }
                R.id.itFavorite -> {
                    changeFragment1(R.id.itFavorite, FavoriteFragment())
                    true
                }
                R.id.itShop -> {
                    changeFragment1(R.id.itShop, ShopFragment())
                    true
                }
                R.id.itSetting -> {
                    changeFragment1(R.id.itSetting, SettingFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed();
        if (lstFragments.size > 1) {
            lstFragments.removeLast()
            binding.bottomNavigation.menu.findItem(lstFragments.last()).isChecked = true
        }
    }

    private fun changeFragment1(int: Int, fragm: Fragment) {
        val ff = supportFragmentManager.beginTransaction()
        ff.replace(binding.FrameLayout.id, fragm)
        ff.commit()
    }

    private fun changeFragment(tagToChange: Int, fragment: Fragment? = null) {
        var addStack: Boolean = false
        val ft = supportFragmentManager.beginTransaction()

        if (lstFragments.isNotEmpty()) {
            val currentFragment =
                supportFragmentManager.findFragmentByTag(lstFragments.last().toString())!!
            val toChangeFragment = supportFragmentManager.findFragmentByTag(tagToChange.toString())
            currentFragment.onPause()

            if (toChangeFragment != null) {
                if (currentFragment != toChangeFragment) {
                    addStack = true
                    ft.setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    );
                    ft.hide(currentFragment).show(toChangeFragment)
                    toChangeFragment.onResume()
                }
            } else {
                if (fragment != null) {
                    addStack = true
                    ft.setCustomAnimations(
                        R.anim.fade_in,
                        R.anim.fade_out
                    );
                    ft.hide(currentFragment)
                        .add(binding.FrameLayout.id, fragment, tagToChange.toString())
                }
            }
        } else {
            if (fragment != null) {
                ft.add(binding.FrameLayout.id, fragment, tagToChange.toString())
                addStack = true
            }
        }

        if (addStack) {
            ft.commit()
            ft.addToBackStack(tagToChange.toString())
            lstFragments.add(tagToChange)
        }
    }
}