package com.haier.healthcenter.testentry;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.haier.smartspeaker.uhomeloginlib.AccountTokenCallBack;
import com.haier.smartspeaker.uhomeloginlib.UhomeAccountManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {
   Button button;
   EditText editText,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //startAnotherApp("com.haier.healthcenter.mirrorhealth");
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherApp("com.haier.healthcenter.mirrorhealth","com.haier.healthcenter.mirrorhealth.healthcenter.LoginLoadtActivity");
               // Beta.checkUpgrade();
                //getToken();
            }
        });
        Log.e("hehe","*********************");
        Beta.checkUpgrade();

    }
    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    private void startAnotherApp(String packname,String classname){

        PackageManager packageManager = getPackageManager();
        if (checkPackInfo(packname)) {
           // Intent intent = packageManager.getLaunchIntentForPackage(packname);
            Intent intent=new Intent();
            intent.setAction("com.android.activity");
           // intent.setClassName(packname,classname);
            intent.putExtra("username",editText.getText().toString());
            intent.putExtra("pwd",editText2.getText().toString());
            Log.e("putbefor","name"+editText.getText().toString()+"pwd:"+editText2.getText());

            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "没有安装" + packname, Toast.LENGTH_SHORT).show();
        }


    }

    private void getToken(){
        UhomeAccountManager.getInstance().getAccountToken(this, new AccountTokenCallBack() {
            @Override
            public void onSucceed(String s) {
                Log.e("token","*****************"+s);
            }

            @Override
            public void onFailure(String s) {
                Log.e("fail","*****************");
            }
        });
    }


}
