package com.inspiringteam.reactivecompass.ReactiveSensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Handler
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import java.util.*

class ReactiveSensors constructor(context: Context){
    private val sensorManager: SensorManager =
            context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    /**
     * Retrieve lists sensors available on device
     *
     * @return list of sensors
     */
    val sensors: List<Sensor>
        get() = sensorManager.getSensorList(Sensor.TYPE_ALL)

//    private constructor() {}

    /**
     * Create ReactiveSensors instance
     *
     * @param context context
     */
//    constructor(context: Context) {
//        this.sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
//    }

    /**
     * Check if device has certain sensor available
     *
     * @param sensorType from Sensor class available in SDK
     */
    fun hasSensor(sensorType: Int): Boolean {
        return sensorManager.getDefaultSensor(sensorType) != null
    }

    /**
     * Returns RxJava Observable, which allows to monitor hardware sensors
     * as a stream of ReactiveSensorEvent object with defined sampling period
     *
     * @param sensorType sensor type from Sensor class from Android SDK
     * @param samplingPeriodInUs sampling period in microseconds,
     * @param handler the Handler the sensor events will be delivered to, use default if it is null
     * you can use predefined values from SensorManager class with prefix SENSOR_DELAY
     * @param strategy BackpressureStrategy for RxJava 2 Flowable type
     * @return RxJava Observable with ReactiveSensorEvent
     */
    @JvmOverloads
    fun observeSensor(sensorType: Int, samplingPeriodInUs: Int = SensorManager.SENSOR_DELAY_NORMAL,
                      handler: Handler? = null, strategy: BackpressureStrategy =
                              BackpressureStrategy.BUFFER): Flowable<SensorEvent> {

        if (!hasSensor(sensorType)) {
            val format = "Sensor with id = %d is not available on this device"
            val message = String.format(Locale.getDefault(), format, sensorType)
            return Flowable.error(SensorNotFoundException(message))
        }

        val sensor = sensorManager.getDefaultSensor(sensorType)
        val wrapper = SensorEventListenerWrapper()
        val listener = wrapper.create()

        return Flowable.create(FlowableOnSubscribe<SensorEvent> { emitter ->
            wrapper.emitter = emitter

            if (handler == null) {
                sensorManager.registerListener(listener, sensor, samplingPeriodInUs)
            } else {
                sensorManager.registerListener(listener, sensor, samplingPeriodInUs, handler)
            }
        }, strategy).doOnCancel { sensorManager.unregisterListener(listener) }
    }

    @JvmOverloads
    fun observeSensors(sensorType1: Int, sensorType2: Int, samplingPeriodInUs: Int,
                       handler: Handler? = null, strategy: BackpressureStrategy = BackpressureStrategy.BUFFER): Flowable<SensorEvent> {

        if (!hasSensor(sensorType1) || !hasSensor(sensorType2)) {
            val format = "Sensors with id = %d  and id = %d are not available on this device"
            val message = String.format(Locale.getDefault(), format, sensorType1, sensorType2)
            return Flowable.error(SensorNotFoundException(message))
        }

        val sensor1 = sensorManager.getDefaultSensor(sensorType1)
        val sensor2 = sensorManager.getDefaultSensor(sensorType2)

        val wrapper = SensorEventListenerWrapper()
        val listener = wrapper.create()

        return Flowable.create(FlowableOnSubscribe<SensorEvent> { emitter ->
            wrapper.emitter = emitter

            if (handler == null) {
                sensorManager.registerListener(listener, sensor1, samplingPeriodInUs)
                sensorManager.registerListener(listener, sensor2, samplingPeriodInUs)
            } else {
                sensorManager.registerListener(listener, sensor1, samplingPeriodInUs, handler)
                sensorManager.registerListener(listener, sensor2, samplingPeriodInUs, handler)
            }
        }, strategy).doOnCancel { sensorManager.unregisterListener(listener) }
    }
}
/**
 * Returns RxJava Flowable, which allows to emit flow of SensorEvents
 * as a stream of objects.
 * Sampling period is set to SensorManager.SENSOR_DELAY_NORMAL.
 *
 * @param sensorType sensor type from Sensor class from Android SDK
 * @return RxJava Observable with ReactiveSensorEvent
 */
/**
 * Returns RxJava Observable, which allows to monitor hardware sensors
 * as a stream of ReactiveSensorEvent object with defined sampling period
 *
 * @param sensorType sensor type from Sensor class from Android SDK
 * @param samplingPeriodInUs sampling period in microseconds,
 * you can use predefined values from SensorManager class with prefix SENSOR_DELAY
 * @return RxJava Observable with ReactiveSensorEvent
 */
