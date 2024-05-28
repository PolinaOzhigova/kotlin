package com.example.lesson20april

import androidx.camera.lifecycle.ProcessCameraProvider
import kotlinx.coroutines.flow.StateFlow

interface CameraProvider {
    val cameraProvider: StateFlow<ProcessCameraProvider?>
}