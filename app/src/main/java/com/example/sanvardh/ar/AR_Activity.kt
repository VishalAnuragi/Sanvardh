package com.example.sanvardh.ar

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.sanvardh.R
import com.google.android.material.snackbar.Snackbar
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import java.util.concurrent.CompletableFuture

class AR_Activity : AppCompatActivity() {

    private lateinit var mContentViewBinding:  ContentViewBinding

    private var arFragment: ArFragment? = null
    private var objectRenderable: ModelRenderable? = null
    private var arModel: String? = null
    private var mModelStateSnackBar: Snackbar? = null
    private lateinit var mModelCompletableFuture: CompletableFuture<Void>
    private var MIN_OPENGL_VERSION = 3.1
    var anchorNode: AnchorNode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar2)

        arModel = "AR_MODEL_URL"
        if (checkIsSupportedDeviceOrFinish(this)) {
            DataBindingUtil.setContentView(this, R.layout.activity_ar2)
            startInitialization()
        } else {

            Toast.makeText(this , "Device not supported for AR" , Toast.LENGTH_LONG).show()
            this.finish()
        }

    }

    private fun startInitialization() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    try {

                        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment


                        Toast.makeText(this, "3D", Toast.LENGTH_SHORT).show()

                        // Init renderable
                        loadModel()

                        // Set tap listener
                        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
                            val anchor = hitResult.createAnchor()
                            if (anchorNode == null) {
                                anchorNode = AnchorNode(anchor)
                                anchorNode?.setParent(arFragment!!.arSceneView.scene)
                                createModel()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, 1)
                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("TAG", e.printStackTrace().toString() + e.message.toString())
        }
    }

    private fun createModel() {
        try {
            if (anchorNode != null) {
                val node = TransformableNode(arFragment!!.transformationSystem)
                node.scaleController.maxScale = 0.02f
                node.scaleController.minScale = 0.01f
                node.setParent(anchorNode)
                node.renderable = objectRenderable

                node.select()
                mModelStateSnackBar = Snackbar.make(mContentViewBinding.arLayout, "model ready", Snackbar.LENGTH_INDEFINITE).setAction("dismiss") {
                    mModelStateSnackBar?.dismiss()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    private fun loadModel() {
        try {
            ModelRenderable.builder()
                .setSource(this,
                    RenderableSource.builder().setSource(
                        this,
                        Uri.parse(arModel),
                        RenderableSource.SourceType.GLB)/*RenderableSource.SourceType.GLTF2)*/
                        .setScale(0.75f)
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                .setRegistryId(arModel)
                .build()
                .thenAccept { renderable: ModelRenderable ->
                    objectRenderable = renderable
                    Toast.makeText(this, "3D", Toast.LENGTH_SHORT).show()

                }
                .exceptionally { throwable: Throwable? ->
                    Log.i("Model", "cant load")
                    mModelStateSnackBar = Snackbar.make(mContentViewBinding.arLayout, "ERROR", Snackbar.LENGTH_INDEFINITE).setAction("Try Again") {
                        mModelStateSnackBar?.dismiss()
                    }
                    null
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e("error", "Sceneform requires Android N or later")
            return false
        }
        val openGlVersionString = (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
            .deviceConfigurationInfo
            .glEsVersion
        if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e("error", "Sceneform requires OpenGL ES 3.1 later")
            return false
        }
        return true
    }
}