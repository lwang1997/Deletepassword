package luo.wang.deletepassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "正在重启手机...", Toast.LENGTH_LONG).show();
                reboot();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    deletePassWord();
            }
        });
    }

    public void deletePassWord() {
        DataOutputStream dataOutputStream = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataOutputStream.writeBytes("rm -r /data/system/*.key\n");
            dataOutputStream.writeBytes("rm /data/system/device_policies.xml\n");
            dataOutputStream.writeBytes("rm /data/system/locksettings.db\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            Toast.makeText(MainActivity.this, "密码清除成功，请重启手机！", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }
    }

    public void reboot() {
        DataOutputStream dataOutputStream = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataOutputStream.writeBytes("reboot\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
        } catch (Exception e) {

        }
    }

}
