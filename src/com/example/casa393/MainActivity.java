package com.example.casa393;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends MinhaActivity {
	Conecta con;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String website = servidor.concat("/query");
		try {
			con = new Conecta(website);
			setContentView(R.layout.activity_main);
			System.out.println("oncreate");
			
		} catch (Excecao e) {
			System.out.println("catch");
			Intent i = new Intent(this, ErroActivity.class);
			startActivity(i);
			this.finish();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(this, ConfiguraActivity.class);
			startActivity(i);
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void mostraLocais(View view) {
		tipoSelecao = "Locais";
		Intent i = new Intent(this, ListaActivity.class);
		startActivity(i);
	}

	public void mostraPavimentos(View view) {
		tipoSelecao = "Pavimentos";
		Intent i = new Intent(this, ListaActivity.class);
		startActivity(i);
	}

	public void mostraSistemas(View view) {
		tipoSelecao = "Sistemas";
		Intent i = new Intent(this, ListaActivity.class);
		startActivity(i);
	}

}
