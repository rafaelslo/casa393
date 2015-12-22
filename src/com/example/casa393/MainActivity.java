package com.example.casa393;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends MinhaActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// Obtem o XML do site
		AsyncTask<String, Integer, String> com = new Comunica(
				getApplicationContext()).execute(servidor.concat("/query"));
		String arquivo;
		try {
			// RAFAEL System.out.println("antes");
			System.out.print("Servidor: ");
			System.out.println(servidor);
			arquivo = com.get(5000, TimeUnit.MILLISECONDS);
			// RAFAEL System.out.println("depois");
			ProcessaXML arqXML = new ProcessaXML();
			arqXML.processa(arquivo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("-----ExecutionException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			erro = "TIMEOUT: Servidor não respondeu";
			e.printStackTrace();
			Intent i = new Intent(this, ErroActivity.class);
			startActivity(i);
			this.finish();
			// setContentView(R.layout.erro_parametros);
			// TODO Auto-generated catch block
		} catch (Exception e) {
			System.out.println("-----Exception");
			e.printStackTrace();
			Intent i = new Intent(this, ErroActivity.class);
			startActivity(i);
			this.finish();
			// setContentView(R.layout.erro_parametros);
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
