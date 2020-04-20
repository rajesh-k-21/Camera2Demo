
package com.madlab.camerademo.helper

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.madlab.camerademo.R
import com.madlab.camerademo.helper.Constants.REQUEST_VIDEO_PERMISSIONS
import com.madlab.camerademo.helper.Constants.VIDEO_PERMISSIONS

class ConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog =
            AlertDialog.Builder(activity)
                    .setMessage(R.string.permission_request)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        parentFragment?.requestPermissions(
                            VIDEO_PERMISSIONS,
                                REQUEST_VIDEO_PERMISSIONS
                        )
                    }
                    .setNegativeButton(android.R.string.cancel) { _,_ ->
                        parentFragment?.activity?.finish()
                    }
                    .create()

}
