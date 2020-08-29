package com.soffanma.vtune

import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.roger.catloadinglibrary.CatLoadingView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    var code: String = ""
    private lateinit var mView: CatLoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.232/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        mView = CatLoadingView()

        etInput.inputType = InputType.TYPE_NULL
        etInput.requestFocus()
        etInput.setText("Hello, Welcome")

        btn1.setOnClickListener {
            code += "1"
            etInput.setText(code)
        }

        btn2.setOnClickListener {
            code += "2"
            etInput.setText(code)
        }

        btn3.setOnClickListener {
            code += "3"
            etInput.setText(code)
        }

        btn4.setOnClickListener {
            code += "4"
            etInput.setText(code)
        }

        btn5.setOnClickListener {
            code += "5"
            etInput.setText(code)
        }

        btn6.setOnClickListener {
            code += "6"
            etInput.setText(code)
        }

        btn7.setOnClickListener {
            code += "7"
            etInput.setText(code)
        }

        btn8.setOnClickListener {
            code += "8"
            etInput.setText(code)
        }

        btn9.setOnClickListener {
            code += "9"
            etInput.setText(code)
        }

        btn0.setOnClickListener {
            code += "0"
            etInput.setText(code)
        }

        btnA.setOnClickListener {
            code += "P"
            etInput.setText(code)
        }   

        btnB.setOnClickListener {
            code = ""
            etInput.setText(code)
        }

        btnCrash.setOnClickListener {
            val service = retrofit.create(relayInterface::class.java)
            mView.show(supportFragmentManager, "")
            val call = service.sendCode(code)
            call.enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@MainActivity, "Test", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    checkCode(code)
                    mView.dismiss()
                }
            })
        }

        btnStar.setOnClickListener {
            etInput.setText("")
            code = "reset"
            val service = retrofit.create(relayInterface::class.java)
            mView.show(supportFragmentManager, "")
            val call = service.sendCode(code)
            call.enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@MainActivity, "Test", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    checkCode(code)
                    mView.dismiss()
                }
            })
        }


    }

    fun checkCode(chCode: String){
        if (chCode == "P115") {
            etInput.setText(chCode + ": ECT Error")
        } else if (chCode == "P110") {
            etInput.setText(chCode + ": IAT Error")
        } else if (chCode == "P016") {
            etInput.setText(chCode + ": CKP Error")
        } else if (chCode == "P351") {
            etInput.setText(chCode + ": CMP Error")
        } else if (chCode == "P001") {
            etInput.setText(chCode + ": Injector Error")
        } else if (chCode == "P120") {
            etInput.setText(chCode + ": TPS Error")
        } else if (chCode == "reset") {
            etInput.setText("Hello, Welcome")
        } else {
            etInput.setText("Wrong Code")
        }
        code = ""
    }

}
