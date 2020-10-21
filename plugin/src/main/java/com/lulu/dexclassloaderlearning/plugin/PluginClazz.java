package com.lulu.dexclassloaderlearning.plugin;

import android.util.Log;

/**
 * @author zhanglulu on 2020/10/16.
 * for 插件测试类
 */
public class PluginClazz {
    private static final String TAG = "PluginTest";

    public PluginClazz() {
        Log.i(TAG, "插件构造方法执行");
    }
    
    public void invoke(String arg) {
        Log.i(TAG, "插件 invoke 方法执行，参数：" + arg);
    }

}
