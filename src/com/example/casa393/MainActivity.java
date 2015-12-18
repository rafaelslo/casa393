package com.example.casa393;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends MinhaActivity {

	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

			setContentView(R.layout.activity_main);

			//Obtem o XML do site
			AsyncTask<String, Integer, String> com = new Comunica(getApplicationContext()).execute(servidor.concat("/query"));
			try {
				// RAFAEL System.out.println("antes");
				String arquivo = com.get(5000, TimeUnit.MILLISECONDS);
				// RAFAEL System.out.println("depois");
				if (arquivo == null) {
					erro = "Arquivo é nulo";
					Exception e = new Exception();
					throw(e);
				} else if (!arquivo.contains("<itens>")) {
					erro = arquivo;
					Exception e = new Exception();
					throw(e);
				}

				XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory
						.newInstance();
				XmlPullParser myParser = xmlFactoryObject.newPullParser();
	
				InputStream stream = new ByteArrayInputStream(arquivo.getBytes());
				
				//InputStream in = getResources().openRawResource(
				//		getResources().getIdentifier("raw/fonte", "raw",
				//				getPackageName()));
	
				myParser.setInput(stream, null);
				myParser.nextTag();
				lista = leArquivo(myParser);
			} catch (InterruptedException e1) {
				System.out.println("InterruptedException");
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				System.out.println("ExecutionException");
				e1.printStackTrace();
			} catch (XmlPullParserException e) {
				System.out.println("xmlPullParserException");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			} catch (TimeoutException e) {
				setContentView(R.layout.erro_timeout);
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				Intent i = new Intent(this, ErroActivity.class);
				startActivity(i);
				this.finish();
				//setContentView(R.layout.erro_parametros);
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList leArquivo(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		ArrayList entries = new ArrayList();

		parser.require(XmlPullParser.START_TAG, ns, "itens");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}

			String name = parser.getName();
			// Starts by looking for the entry tag
			if (name.equals("item")) {
				ItemSensor itemSensor = new ItemSensor(parser);
				entries.add(itemSensor);
			} else {
				// skip(parser);
			}
		}
		return entries;
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
