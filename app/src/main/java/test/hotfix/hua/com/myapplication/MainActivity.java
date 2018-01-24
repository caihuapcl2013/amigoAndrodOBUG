package test.hotfix.hua.com.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;

import me.ele.amigo.Amigo;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout=(FrameLayout) findViewById(R.id.test_layout);
        View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout,null);
        frameLayout.addView(view);

        view.findViewById(R.id.load_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePath();
            }
        });

        String [] strings={Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions( MainActivity.this, strings,0);
    }


    protected   void updatePath(){
        File file =new File(Environment.getExternalStorageDirectory(),"test.apk");
        Amigo.workLater(getApplicationContext(), file, new Amigo.WorkLaterCallback() {
            @Override
            public void onPatchApkReleased(boolean b) {
                Log.d("HUA","onPatchApkReleased: "+b);

            }
        });
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
        }

    }
}
