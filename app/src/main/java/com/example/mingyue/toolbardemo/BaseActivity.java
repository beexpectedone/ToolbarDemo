package com.example.mingyue.toolbardemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by mingyue on 2017/2/24.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * 1.gradle 文件当中引入v7包
     * 2.创建一个toolbar_layout  包含Toolbar的xml文件。 特别注意设置其中的paddingTop 属性，这个属性和状态栏的设置有关
     * 3.新建values-v19文件夹，再在该文件夹下新建styles 和 dimens xml文件，在styles文件中设置一个style "<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">"
     * 4.实现BaseActivity，继承自AppCompatactivity，在 onCreate方法中注意加上"super.setContentView(R.layout.activity_base);"在底层做更改其xml文件，给每个继承BaseActivity的类都添加上
     *   Toolbar这个标题栏。
     * 5.在BaseActivity类中init（初始化）Toolbar，之后在子类可以获取该Toolbar设置title，还可以设置返回键，自定义图标或者是用系统的图标
     * */
    private LinearLayout rootLayout;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);  //从底层将状态都设置好
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        initToolbar();
    }


    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        /**
         * 如果有需要设置menu的话，在子类中去具体的添加即可。
         * */
    }

}
