package com.example.dyncdemo;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

//import com.mdc.AccessMDC;
//import com.mdc.CltSMDC;
import com.mdc.at.sdcs.DUClt;
import com.mob.MobSDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.commons.oaid.FidsSDK;
import com.mob.tools.utils.Hashon;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements View.OnClickListener {
	private Hashon hashon;
	private String duid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hashon = new Hashon();
		checkPermissions();
		initMobCommons();
		initView();
		checkDuid();
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_access_mdc: {
				initAccessMdc();
				break;
			}
			case R.id.btn_clts_mdc: {
				initCltsMdc();
				break;
			}
			case R.id.btn_test_mdc: {
				testMdc();
				break;
			}
		}
	}

	private void initView() {
		findViewById(R.id.btn_access_mdc).setOnClickListener(this);
		findViewById(R.id.btn_clts_mdc).setOnClickListener(this);
		findViewById(R.id.btn_test_mdc).setOnClickListener(this);
	}

	private void initAccessMdc() {
//		AccessMDC.init(duid);
	}

	private void initCltsMdc() {
		String params;
		HashMap<String, Object> map = new HashMap<>();
		map.put("duid", duid);

		params = hashon.fromHashMap(map);
//		CltSMDC.init(params);
	}

	/* 检查使用权限 */
	protected void checkPermissions() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			try {
				PackageManager pm = getPackageManager();
				PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
				ArrayList<String> list = new ArrayList<String>();
				for (String p : pi.requestedPermissions) {
					if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
						list.add(p);
					}
				}
				if (list.size() > 0) {
					String[] permissions = list.toArray(new String[list.size()]);
					if (permissions != null) {
						requestPermissions(permissions, 1);
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private void initMobCommons() {
		MobSDK.init(this);
		duid = DeviceAuthorizer.authorize(null);
	}

	private void checkDuid() {
		if (TextUtils.isEmpty(duid)) {
			duid = "test_duid_jackie";
		}
	}

	private void testMdc() {
		DUClt.start(this, FidsSDK.getOAID(this), "test_duid", "fe0614078730");
	}
}
