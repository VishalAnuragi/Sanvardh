package com.example.sanvardh.ar


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sanvardh.R
import com.example.sanvardh.modelsActivity.Models_Activity
import com.google.ar.sceneform.ux.ArFragment


class AR_Activity : AppCompatActivity() {


    private var arFragment: ArFragment? = null
    var model : String ?= null
    var link : String ?= null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar2)
        val bundle = intent.extras
        model = bundle!!.getString("MODEL")

        when (model){

            "CUBE" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Cube/glTF/Cube.gltf"
            "ANTIQUE CAMERA" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/AntiqueCamera/glTF/AntiqueCamera.gltf"
            "BOOM BOX" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BoomBox/glTF/BoomBox.gltf"
            "SCIFI HELMET" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/SciFiHelmet/glTF/SciFiHelmet.gltf"
            "FISH" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BarramundiFish/glTF/BarramundiFish.gltf"
            "FOX" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Fox/glTF/Fox.gltf"
            "AVACADO" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Avocado/glTF/Avocado.gltf"
            "ENGINE" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/2CylinderEngine/glTF/2CylinderEngine.gltf"
            "GEARBOX" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/GearboxAssy/glTF/GearboxAssy.gltf"
            "SAW" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/ReciprocatingSaw/glTF/ReciprocatingSaw.gltf"
            "TRUCK" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/CesiumMilkTruck/glTF/CesiumMilkTruck.gltf"
            "ROBOT" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BrainStem/glTF/BrainStem.gltf"
            "SPONZA" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Sponza/glTF/Sponza.gltf"
            "VIRTUAL CITY" -> link = "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/VC/glTF/VC.gltf"
        }

      //  arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment?



        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)

        val intentUri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
            .appendQueryParameter(
                "file",
                link
            )
            .appendQueryParameter("mode", "3d_preferred")

            .build()
        sceneViewerIntent.data = intentUri
        sceneViewerIntent.setPackage("com.google.ar.core")
        startActivity(sceneViewerIntent)


        }
/*
    override fun onBackPressed() {
        super.onBackPressed()

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val topic = preferences.getString("TOPIC", "")


        val intent = Intent(applicationContext, Models_Activity::class.java).apply {
            putExtra("TOPIC", topic)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        applicationContext.startActivity(intent)
    }

 */
}