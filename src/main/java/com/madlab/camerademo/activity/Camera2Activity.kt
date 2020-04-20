package com.madlab.camerademo.activity


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.madlab.camerademo.R
import com.madlab.camerademo.fragment.Camera2PhotoFragment
import com.madlab.camerademo.fragment.Camera2VideoFragment
import kotlinx.android.synthetic.main.activity_camera2.*

class Camera2Activity : AppCompatActivity(), View.OnClickListener {

    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)
        buttonPhoto.setOnClickListener(this)
        buttonRecodingVideo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonPhoto -> {
                if (fragmentManager.backStackEntryCount != 0) {
                    fragmentManager.popBackStack()
                    replaceFragment(Camera2PhotoFragment(), true)
                } else {
                    replaceFragment(Camera2PhotoFragment(), true)
                }
            }
            R.id.buttonRecodingVideo -> {
                if (fragmentManager.backStackEntryCount != 0) {
                    fragmentManager.popBackStack()
                    replaceFragment(Camera2VideoFragment(), true)
                } else {
                    replaceFragment(Camera2VideoFragment(), true)
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean = false) {
        val beginTransaction = fragmentManager.beginTransaction()
        beginTransaction.replace(R.id.placeHolder, fragment)

        if (isAddToBackStack) {
            beginTransaction.addToBackStack(fragment::class.java.name)
        }
        beginTransaction.commit()
    }
}