package com.arduino.bluetooth10.activities

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.arduino.bluetooth10.R
import com.arduino.bluetooth10.utils.BlueLib

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

   // lateinit var disp : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     //   disp = findViewById(R.id.disp)
        val blue = BlueLib(this, disp, Main2Activity::class.java)
        //para listView normal solo accedemos a onBluetooth
        blue.onBluetooth()

        //para listView Custom return ArrayList<String>
            //val a = blue.dispEmparejados()
            //blue.bluetoothSeleccionAddres("Addres")
        //para listView Custom return ArrayList<String>

        disp.setOnItemClickListener { adapterView, view, i, l ->
            blue.bluetoothSeleccion(i)
        }
    }
}