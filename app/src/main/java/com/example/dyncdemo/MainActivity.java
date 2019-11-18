package com.example.dyncdemo;

import android.app.Activity;
import android.os.Bundle;

import com.mdc.AccessMDC;

public class MainActivity extends Activity {

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
