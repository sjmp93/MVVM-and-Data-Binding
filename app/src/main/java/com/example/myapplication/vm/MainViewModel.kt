package com.example.myapplication.vm

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.TextData

class MainViewModel : ViewModel(){
    val data: MutableLiveData<TextData>
    var btDevice: BluetoothAdapter
    var btEventReceiver : BroadcastReceiver

    init{
        data = MutableLiveData()
        btDevice = BluetoothAdapter.getDefaultAdapter()
        data.value = TextData(binary = btDevice.isEnabled)
        btEventReceiver = object : BroadcastReceiver(){
            override fun onReceive(contxt: Context?, intent: Intent?){
                System.out.println("BT state switched from ${btDevice.isEnabled} to ${!btDevice.isEnabled}")
                //To avoid triggering switch method twice (during intermediate state and final state) we check State inside intent extra
                if ((intent!!.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_ON ||
                    intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) &&
                    data!!.value!!.binary != btDevice.isEnabled) {
                    switchBT()
                }
            }
        }
    }

    fun switchBT(){
        var _data = data.value ?: TextData()
        _data.commute()
        data.value = _data
    }

    fun _switchBT(){
        switchBT()
        if(data.value!!.binary) btDevice.enable() else btDevice.disable()
    }
}