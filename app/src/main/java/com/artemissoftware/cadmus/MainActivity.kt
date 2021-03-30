package com.artemissoftware.cadmus

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startHuaweiAppGallery("102312221");
    }


    private fun startHuaweiAppGallery(appHuaweiId: String) {

        var intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://appgallery.huawei.com/#/app/C102312221")).setPackage(packageName)

        val otherApps = packageManager.queryIntentActivities(intent, 0)
        var agFound = false

        for (app in otherApps) {
            if (app.activityInfo.applicationInfo.packageName == "com.huawei.appmarket") {

                val psComponent = ComponentName(app.activityInfo.applicationInfo.packageName, app.activityInfo.name)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_CLEAR_TOP)

                intent.component = psComponent
                startActivity(intent)
                agFound = true
                break
            }
        }

        if (!agFound) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://appgallery.cloud.huawei.com/marketshare/app/C$appHuaweiId"))
            startActivity(intent)
        }
    }

}