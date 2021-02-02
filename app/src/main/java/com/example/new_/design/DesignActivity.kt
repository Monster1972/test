package com.example.new_.design

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.new_.design.proxy.Login
import com.example.new_.design.proxy.LoginProxy1
import com.example.new_.design.proxy.LoginProxy2
import com.example.new_.design.proxy.LoginProxy3
import com.example.new_.design.proxy.api.ILogin

/*
 * 
 * @Author: dottang
 * @Date: 2021/1/31
 */
class DesignActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun startActivity(context: Context):Unit {
            context.startActivity(Intent(context, DesignActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runProxy()
    }

    /**
     * 弊端：
     * 手动代理模式需要手动"克隆"另一个代理类（包括其中的所有接口）
     * 过多的类、过多的接口，都将产生不必要的开发成本
     */
    private fun runProxy() {

        val loginProxy1 = LoginProxy1()
        loginProxy1.login("loginProxy1");

        val loginProxy2 = LoginProxy2()
        loginProxy2.login("loginProxy2")

        val loginProxy3 = LoginProxy3()
        val login = loginProxy3.createLoginProxy(Login()) as ILogin
        login.login("loginProxy3")
    }

}