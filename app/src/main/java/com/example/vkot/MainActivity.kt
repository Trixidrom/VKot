package com.example.vkot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.utils.VKUtils.getCertificateFingerprint
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val VKArr = intArrayOf(4507821,34637783, 155096331)
        val VKReq = VKUsersRequest(VKArr)
//        val tokenTracker = object: VKTokenExpiredHandler {
//            override fun onTokenExpired() {
//                // token expired
//            }
//        }
//        VK.addTokenExpiredHandler(tokenTracker)
//
       VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS))

        bGo.setOnClickListener{
            tvResult.text = "Нажали го!"
            VK.execute(VKReq, object: VKApiCallback<List<VKUser>> {
                override fun success(result: List<VKUser>) {
                }

                override fun fail(error: Exception) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                tvResult.text = "авторизовались"
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}


