package com.example.sanvardh.ar


import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sanvardh.R
import com.google.ar.core.Anchor
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class AR_Activity : AppCompatActivity() {


    private var arFragment: ArFragment? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar2)

        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment?

        // adding listener for detecting plane

        // adding listener for detecting plane
        arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane?, motionEvent: MotionEvent? ->
            val anchor = hitResult.createAnchor()

            // adding model to the scene
            ModelRenderable.builder()
                .setSource(this, Uri.parse("TocoToucan.sfb"))
                .build()
                .thenAccept { modelRenderable: ModelRenderable? ->
                    if (modelRenderable != null) {
                        addModelToScene(anchor, modelRenderable)
                    }
                }
        }

    }

    private fun addModelToScene(anchor: Anchor, modelRenderable: ModelRenderable) {
        val node = AnchorNode(anchor)
        val transformableNode =
            TransformableNode(arFragment!!.transformationSystem) //  for moving, resizing object
        transformableNode.setParent(node) // need to attach to parent
        transformableNode.renderable = modelRenderable
        arFragment!!.arSceneView.scene.addChild(node) // adding only parent node, so the child nodes will be added automatically
        transformableNode.select()
    }

}