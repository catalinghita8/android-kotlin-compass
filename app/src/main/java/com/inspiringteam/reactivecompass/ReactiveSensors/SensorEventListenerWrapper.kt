package com.inspiringteam.reactivecompass.ReactiveSensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

import io.reactivex.FlowableEmitter

class SensorEventListenerWrapper {

    var emitter: FlowableEmitter<SensorEvent>? = null

    fun create(): SensorEventListener {
        return object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                if (emitter != null) {
                    emitter!!.onNext(sensorEvent)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                if (emitter != null) {
                    // TODO - implement if needed
                }
            }
        }
    }
}
