package com.example.casa393;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ErroActivity extends MinhaActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.erro_parametros);
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		tv1.setText(erro);

	}
	
	public void showSettings(View view) {
		Intent i = new Intent(this, ConfiguraActivity.class);
		startActivity(i);
		this.finish();
	}
	
	public void showMain(View view) {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		this.finish();
	}
	
}
