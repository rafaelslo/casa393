package com.example.casa393;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class ListaItensActivity extends MinhaActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_itens);
		carregaLista();
	}

	private void carregaLista() {
		getActionBar().setTitle(selecao);
		ArrayList <ItemSensor> listaAplicada = new ArrayList<ItemSensor>();
		String compara = "";
		for (ItemSensor i : lista) {
			if(tipoSelecao.equals("Locais")) {
				compara = i.getLocal();
			} else if(tipoSelecao.equals("Pavimentos")) {
				compara = i.getPavimento();
			} else if(tipoSelecao.equals("Sistemas")) {
				compara = i.getSistema();
			}

			if (!listaAplicada.contains(i) && selecao.equals(compara)) {
				listaAplicada.add(i);
			}
		}

		ItemAdapter adapter = new ItemAdapter(this,listaAplicada);

		// Cria uma referência para a ListView
		final ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_itens, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.refresh) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
