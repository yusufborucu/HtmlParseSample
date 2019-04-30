package com.yusufborucu.htmlparsesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static ListView lvPharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPharmacy = findViewById(R.id.lvPharmacy);

        new PharmacyTask(this).execute();
    }
}
