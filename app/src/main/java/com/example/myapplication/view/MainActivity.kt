package com.example.myapplication.view

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.data.TextData
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.vm.MainViewModel


class MainActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST = 0
    private lateinit var viewModel: MainViewModel
    private val btDevice = BluetoothAdapter.getDefaultAdapter()

    private val btEventReceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(contxt: Context?, intent: Intent?){
            System.out.println("Switching BT state from ${btDevice.isEnabled} to ${!btDevice.isEnabled}")
            //To avoid triggering switch method twice (during intermediate state and final state) we check State inside intent extra
            if ((intent!!.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_ON ||
                        intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) &&
                viewModel.data!!.value!!.binary != btDevice.isEnabled) {
                viewModel.switchBT()

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Binding with layout file
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //Bind boolean value with BT device state (if BT adapter is enabled, then, the app starts with that state for TextData...)
        viewModel.data.value = TextData(binary = btDevice.isEnabled)

        //Observe changes at data to switch btDevice state when data is changed
        viewModel.data.observe(this, Observer<TextData>(){
             System.out.println("Observer triggered, switching BT state from ${btDevice.isEnabled} to ${!btDevice.isEnabled}")
            if(viewModel.data.value!!.binary) btDevice.enable() else btDevice.disable()
        })

        //Request corresponging permissions for BT functionality
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN),
            MY_PERMISSIONS_REQUEST);

        //Binding ViewModel with view
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()

        //Setup for Bluetooth's state changes event receiver
        val bluetoothStatusIntent = Intent(BluetoothAdapter.ACTION_STATE_CHANGED)
        val intentFilter = IntentFilter(bluetoothStatusIntent.action)
        registerReceiver(btEventReceiver, intentFilter)
    }
}
