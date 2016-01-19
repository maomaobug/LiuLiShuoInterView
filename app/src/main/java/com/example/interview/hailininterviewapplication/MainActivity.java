package com.example.interview.hailininterviewapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.interview.hailininterviewapplication.demodata.DemoData;
import com.example.interview.hailininterviewapplication.thirdparty.FuriganaView;

public class MainActivity extends AppCompatActivity {

    private DemoView demoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.demoView = (DemoView) findViewById(R.id.main_demo);

        showDemoText();
    }

    private void showDemoText() {
        demoView.setFuriganaText(DemoData.demoModels);
    }
}
