package com.example.dyncdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mdc.AccessMDC;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initMdc();
	}

	private void initMdc() {

		AccessMDC.init("test_duid_shijie");
	}
}
