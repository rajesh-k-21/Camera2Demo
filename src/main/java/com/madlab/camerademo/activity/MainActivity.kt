package com.madlab.camerademo.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.madlab.camerademo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val VIDEO_CAPTURE = 101
        const val PHOTO_CAPTURE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClickPhoto.setOnClickListener(this)
        buttonRecodingVideo.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonClickPhoto -> {
                clickPhoto()
            }
            R.id.buttonRecodingVideo -> {
                recodingVideo()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VIDEO_CAPTURE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(
                        this, "Video saved to:\n" +
                                data?.data?.path, Toast.LENGTH_LONG
                    ).show()
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(
                        this, "Video recording cancelled.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        this, "Failed to record video",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        if (requestCode == PHOTO_CAPTURE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(
                        this, "photo saved to:\n" +
                                data?.extras?.get("data"), Toast.LENGTH_LONG
                    ).show()
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(
                        this, "Photo Capture cancelled.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        this, "Failed to Capture photo",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun clickPhoto() {
        if (checkPermissionForCamera()) {
            if (hasCamera()) {
                startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                    PHOTO_CAPTURE
                )
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.string_camera_not_avalible),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            requestCameraPermission()
        }
    }

    private fun recodingVideo() {
        if (checkPermissionForCamera()) {
            if (hasCamera()) {
                startActivityForResult(
                    Intent(MediaStore.ACTION_VIDEO_CAPTURE),
                    VIDEO_CAPTURE
                )
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.string_camera_not_avalible),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            requestCameraPermission()
        }
    }

    private fun hasCamera(): Boolean {
        return (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) && packageManager.hasSystemFeature(
            PackageManager.FEATURE_CAMERA_ANY
        ))
    }

    private fun checkPermissionForCamera(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            PHOTO_CAPTURE
        )
    }
}
