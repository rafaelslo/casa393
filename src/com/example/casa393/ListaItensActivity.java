package com.example.casa393;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ListaItensActivity extends MinhaActivity {
	Conecta con;

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

		// Cria uma refer�ncia para a ListView
		final ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_itens, menu);
		getActionBar().setDisplayHomeAsUpEnabled(false);
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
	
	public void btnOnOffClick(View view){
		String estado;
		ToggleButton tb1 = (ToggleButton) view.findViewById(R.id.toggleButton1);
		String id = tb1.getTag().toString();
		if(tb1.isChecked()) {
			estado = "1";
		} else { 
			estado = "0";
		}
			
		//Obtem o XML do site
		String website = servidor.concat("/set/").concat(id).concat("/").concat(estado).concat("/");
		try {
			con = new Conecta(website);
			for (ItemSensor item : lista) {
				if(item.getId().equals(id)) {
					if(!item.getValor().equals(estado)) {
						Toast t = Toast.makeText(getApplicationContext(), "Nao foi possivel alterar o valor", Toast.LENGTH_SHORT);
						t.show();
					}
					break;
				}
			}
		} catch (Excecao e) {
			Intent i = new Intent(this, ErroActivity.class);
			startActivity(i);
			this.finish();
		}		
		
		

	}
}
