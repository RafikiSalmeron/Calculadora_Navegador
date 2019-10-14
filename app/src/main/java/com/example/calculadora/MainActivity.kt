package com.example.calculadora

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var memoria = 0.0
    var valor1 = ""
    var resultado = 0.0
    var op = ""
    var ft = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            wb_main.webChromeClient = object : WebChromeClient() {}

            wb_main.webViewClient = object : WebViewClient() {}

            val settings = wb_main.settings
            settings.javaScriptEnabled = true

            wb_main.loadUrl("https://www.google.es")

            searchView2.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query.let {
                        if (URLUtil.isValidUrl(it)) {
                            //URL VALIDA
                            wb_main.loadUrl(it)
                        } else {
                            //URL INVALIDA
                            wb_main.loadUrl("https://www.google.com/search?q="+it)
                        }

                    }
                    return false
                }


            })


        }
    }

    override fun onBackPressed() {
        if (wb_main.canGoBack()) {
            wb_main.goBack()
        } else {
            super.onBackPressed()
        }
    }

    fun numeros(view: View) {
        when (view.id) {
            R.id.btpunto -> {
                valor1 += "."
                tvResultado.setText(valor1)
            }
            R.id.bt0 -> {
                valor1 += "0"
                tvResultado.setText(valor1)
            }

            R.id.bt1 -> {
                valor1 += "1"
                tvResultado.setText(valor1)
            }

            R.id.bt2 -> {
                valor1 += "2"
                tvResultado.setText(valor1)
            }

            R.id.bt3 -> {
                valor1 += "3"
                tvResultado.setText(valor1)
            }

            R.id.bt4 -> {
                valor1 += "4"
                tvResultado.setText(valor1)
            }

            R.id.bt5 -> {
                valor1 += "5"
                tvResultado.setText(valor1)
            }

            R.id.bt6 -> {
                valor1 += "6"
                tvResultado.setText(valor1)
            }

            R.id.bt7 -> {
                valor1 += "7"
                tvResultado.setText(valor1)
            }

            R.id.bt8 -> {
                valor1 += "8"
                tvResultado.setText(valor1)
            }

            R.id.bt9 -> {
                valor1 += "9"

                tvResultado.setText(valor1)
            }
        }

    }

    fun suma(view: View) {
        resultado = valor1.toDouble() + resultado

        tvrecorrido.text = tvrecorrido.text.toString() + tvResultado.text + "+"
        valor1 = ""
        tvResultado.setText(valor1)
        op = "s"
    }

    fun masAns(view: View) {
        resultado = memoria + resultado

        tvrecorrido.text = tvrecorrido.text.toString() + tvResultado.text + "+" + memoria
        valor1 = ""
        tvResultado.setText(valor1)
        valor1 = "0"
        op = "s"

    }

    fun ans(view: View) {
        valor1 = "" + memoria
        resultado = 0.0
        tvrecorrido.setText("")
        tvResultado.setText(valor1)

    }

    fun borrar(view: View) {


        valor1 = ""
        resultado = 0.0
        op = ""
        ft = 0

        //Toast.makeText(applicationContext, ""+resultado, Toast.LENGTH_LONG).show()
        tvrecorrido.setText("")
        tvResultado.setText("0")

    }

    fun resetAns(view: View) {
        memoria = 0.0
    }

    fun resta(view: View) {
        if (resultado != 0.0) {
            resultado = resultado - valor1.toDouble()
        } else {
            resultado = valor1.toDouble() - resultado
        }

        tvrecorrido.text = tvrecorrido.text.toString() + tvResultado.text + "-"
        valor1 = ""
        tvResultado.setText(valor1)
        op = "r"
    }

    fun dividir(view: View) {
        if (op.equals("d")) {
            ft = 1
        }

        if (ft == 0) {
            resultado = 1.0
            resultado = tvResultado.text.toString().toDouble() / resultado
        }//else if(ft==2){
        //resultado = resultado / tvResultado.text.toString().toDouble()
        else {
            resultado = resultado / tvResultado.text.toString().toDouble()
        }




        tvrecorrido.text = tvrecorrido.text.toString() + tvResultado.text + "/"
        valor1 = ""
        tvResultado.setText(valor1)
        op = "d"
    }

    fun multi(view: View) {
        if (ft == 0) {
            resultado = 1.0
        }

        resultado = tvResultado.text.toString().toDouble() * resultado

        tvrecorrido.text = tvrecorrido.text.toString() + tvResultado.text + "*"
        valor1 = ""
        tvResultado.setText(valor1)
        op = "m"
        ft = 1
    }


    fun igual(view: View) {
        when (op) {
            "s" -> resultado = resultado + valor1.toDouble()
            "d" -> {
                resultado = resultado / tvResultado.text.toString().toDouble()
                ft = 0
            }
            "r" -> resultado = resultado - tvResultado.text.toString().toDouble()
            "m" -> {
                resultado = resultado * tvResultado.text.toString().toDouble()
                ft = 0
            }
        }
        // valor1 = resultado.toString()
        memoria = resultado
        tvrecorrido.text = ""
        if (resultado.toString().substring(resultado.toString().indexOf(".")).toDouble() != 0.0) {
            tvResultado.text = resultado.toString()
        } else {
            tvResultado.text = resultado.toString().substring(0, resultado.toString().indexOf("."))
        }

        valor1 = "0"

    }

}
