package com.fintech.demo.presentation.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fintech.demo.databinding.FragmentCameraBinding
import com.fintech.demo.presentation.ui.alert.Alerter
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors


@androidx.camera.core.ExperimentalGetImage
class CameraFragment : Fragment() {
    private val mAlerter by lazy { Alerter() }

    private lateinit var previewView: PreviewView
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var barcodeScanner: BarcodeScanner

    private lateinit var mBinding: FragmentCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentCameraBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewView = mBinding.previewView
        barcodeScanner = BarcodeScanning.getClient()

        requestPermissions.launch(arrayOf(android.Manifest.permission.CAMERA))
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[android.Manifest.permission.CAMERA] == true) {
                startCamera()
            } else {
                mAlerter.showSuccess(
                    "Permission", "Camera permission is required.", requireActivity()
                )
            }
        }

    private fun startCamera() {
        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(this@CameraFragment.requireActivity())
        cameraProviderFuture.addListener(Runnable {
            cameraProvider = cameraProviderFuture.get()
            bindPreviewUseCase()
        }, ContextCompat.getMainExecutor(this@CameraFragment.requireActivity()))
    }

    private fun bindPreviewUseCase() {
        val preview = Preview.Builder().build()
        preview.setSurfaceProvider(previewView.surfaceProvider)

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        val imageAnalysis = ImageAnalysis.Builder().build().also {
            it.setAnalyzer(Executors.newSingleThreadExecutor(), { imageProxy ->
                processImageProxy(imageProxy)
            })
        }

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
    }

    private fun processImageProxy(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            val result: Task<List<Barcode>> =
                barcodeScanner.process(image).addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        val rawValue = barcode.rawValue
                        // Handle the QR code value as needed
                        mAlerter.showSuccess("Created", "$rawValue", requireActivity())
                    }
                }.addOnFailureListener {
                    //Log.e("MainActivity", "QR Code scanning failed: ${it.message}")
                    mAlerter.showError("Created", "${it.message}", requireActivity())
                }.addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = CameraFragment().apply {
            arguments = Bundle().apply {}
        }
    }
}