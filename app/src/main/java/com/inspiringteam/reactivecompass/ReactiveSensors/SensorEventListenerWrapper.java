package com.inspiringteam.reactivecompass.ReactiveSensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import io.reactivex.FlowableEmitter;

public class SensorEventListenerWrapper {

    private FlowableEmitter<SensorEvent> emitter;

    public FlowableEmitter<SensorEvent> getEmitter() {
        return emitter;
    }

    public void setEmitter(FlowableEmitter<SensorEvent> emitter) {
        this.emitter = emitter;
    }

    public SensorEventListener create() {
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (getEmitter() != null) {
                    getEmitter().onNext(sensorEvent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                if (getEmitter() != null) {
                    // TODO - implement if needed
                }
            }
        };
    }
}
