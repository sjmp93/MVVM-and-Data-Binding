package com.example.myapplication.view

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.vm.MainViewModel


class MainActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST = 0
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)
        viewModel = MainViewModel()


        //binding.btDevice = bluetoothAdapter

        //Set BT initial status according to system status
        //TODO
        //if(bluetoothAdapter.isEnabled == binding.viewmodel.data.value!!.binary)
        //    binding.viewmodel.switchBT()
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN),
            MY_PERMISSIONS_REQUEST);

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        val bluetoothStatusIntent = Intent(BluetoothAdapter.ACTION_STATE_CHANGED)
        val intentFilter = IntentFilter(bluetoothStatusIntent.action)
        //eventReceiver!!.onReceive(this, bluetoothStatusIntent)
        registerReceiver(viewModel.btEventReceiver, intentFilter)
    }
}
