package com.example.casa393;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;

public class ListaActivity extends MinhaActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		carregaLista();

	}

	protected void carregaLista() {
		getActionBar().setTitle(tipoSelecao);
		ArrayList<String> l = new ArrayList<String>();
		String compara = "";
		for (ItemSensor i : lista) {
			if(tipoSelecao.equals("Locais")) {
				compara = i.getLocal();
			} else if(tipoSelecao.equals("Pavimentos")) {
				compara = i.getPavimento();
			} else if(tipoSelecao.equals("Sistemas")) {
				compara = i.getSistema();
			}
			if (!l.contains(compara)) {
				l.add(compara);
			}
		}
		

		ArrayAdapter<String> listaItens = new ArrayAdapter<String>(this, // O contexto atual
				R.layout.list_item_locais, // O arquivo de layout de cada item
				R.id.list_item_post_title_textview, // O ID do campo a ser
													// preenchido
				l // A fonte dos dados
		);

		// Cria uma referência para a ListView
		final ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(listaItens);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item value
				String itemValue = (String) listView
						.getItemAtPosition(position);
				selecao = itemValue;

				Intent i = new Intent();
				i.setClass(getApplicationContext(), ListaItensActivity.class);
				startActivity(i);
				
				// Show Alert
				//Toast.makeText(
				//		getApplicationContext(),
				//		"Position :" + itemPosition + "  ListItem : "
				//				+ itemValue, Toast.LENGTH_LONG).show();

			}
		});

	}

}
