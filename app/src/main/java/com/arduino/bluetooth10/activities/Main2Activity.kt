package com.arduino.bluetooth10.activities

import kotlinx.android.synthetic.main.activity_main2.*
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arduino.bluetooth10.R
import com.arduino.bluetooth10.utils.BlueLib
import kotlin.concurrent.thread

class Main2Activity : AppCompatActivity() {

    lateinit var blue :BlueLib
    var initConexion = false
    var offHilo = false

    //lateinit var envia: Button
    //lateinit var pantallaRx: TextView
    //lateinit var textoEnviar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //envia = findViewById(R.id.Enviar)
        //pantallaRx = findViewById(R.id.Consola)
        //textoEnviar = findViewById(R.id.TextoEnviar)

        blue = BlueLib(this, MainActivity::class.java)
        //si se pierde conexion no sale sino que avisa con un mensaje  error
        blue.exitErrorOk(true)
        //mensaje conectado
        blue.mensajeConexion("Conectado ?")
        //mensaje de error
        blue.mensajeErrorTx("algo salio mal")

        thread(start = true){
            while (!initConexion && !offHilo){
                Thread.sleep(500)
            }
            while (!offHilo){
                var mensaje = blue.mRx()
                if (mensaje!=""){
                    pantallaRx.post {
                        pantallaRx.text = mensaje
                    }
                }
                Thread.sleep(100)
            }
        }

        envia.setOnClickListener {
            blue.mTx("Hola")
        }
        envia.setOnLongClickListener {
            blue.exitConexion()
            offHilo = true
            false
        }


    }
    override fun onResume() {
        initConexion =  blue.conectaBluetooth()
        super.onResume()
    }
    override fun onPause() {
        offHilo = true
        blue.exitConexion()
        super.onPause()
    }
}
