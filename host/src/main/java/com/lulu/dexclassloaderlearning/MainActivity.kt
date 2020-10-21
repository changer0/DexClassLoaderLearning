package com.lulu.dexclassloaderlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private var pluginFilePath = ""
    private var pluginFile = "plugin.apk"
    private var pluginPath = "plugin.apk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pluginPath = cacheDir.absolutePath + "/plugin/"
        pluginFilePath = pluginPath + pluginFile
        val file = File(pluginPath)
        if (file.exists().not()) {
            file.mkdirs()
        }
        val inputStream = assets.open("plugin.apk")
        val fileOutputStream = FileOutputStream(File(pluginFilePath))
        IoUtils.copyStream(inputStream, fileOutputStream, null)
        loadPluginClass.setOnClickListener {
            loadPluginApk()
        }
    }
    private fun loadPluginApk() {

        val loader = DexClassLoader(pluginFilePath, pluginPath, null, classLoader)
        val clazz = loader.loadClass("com.lulu.dexclassloaderlearning.plugin.PluginClazz")
        val invokeMethod = clazz.getMethod("invoke", String::class.java)
        val newInstance = clazz.newInstance()
        invokeMethod.invoke(newInstance, "宿主字符串")
    }
}