package com.example.casa393;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;

public class ConfiguraActivity extends MinhaActivity {
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configura);
		Dados dados = new Dados(getApplicationContext());
		EditText tv1 = (EditText) findViewById(R.id.editText1);
		EditText tv2 = (EditText) findViewById(R.id.editText2);
		tv1.setText(dados.getDados("SERVIDOR"));
		tv2.setText(dados.getDados("SENHA"));
	}

	public void btnCancel(View view){
		if (validaCampos()) {
			intent = new Intent(this, MainActivity.class);
		} else {
			intent = new Intent(this, ErroActivity.class);
		}
		startActivity(intent);
		this.finish();
	}

	public void btnSave(View view){
		Dados dados = new Dados(getApplicationContext());
		EditText tv1 = (EditText) findViewById(R.id.editText1);
		EditText tv2 = (EditText) findViewById(R.id.editText2);
		if (validaCampos()) {
			Editable eservidor = tv1.getText();
			Editable esenha = tv2.getText();
			dados.salvarDados(eservidor.toString(), esenha.toString());
			servidor = eservidor.toString();
			senha = esenha.toString();
			intent = new Intent(this, MainActivity.class);
		} else {
			intent = new Intent(this, ErroActivity.class);
		}
		startActivity(intent);
		this.finish();
	}
	
	private boolean validaCampos() {
		EditText tv1 = (EditText) findViewById(R.id.editText1);
		EditText tv2 = (EditText) findViewById(R.id.editText2);
		// RAFAEL System.out.println("tv1.getText():" +  tv1.getText() + "-");
		// RAFAEL System.out.println("tv2.getText():" +  tv2.getText() + "-");
		// RAFAEL System.out.println("tv1.getText().eq: " +  tv1.getText().equals(""));
		// RAFAEL System.out.println("tv2.getText().eq: " +  tv2.getText().equals(""));
		if (tv1.getText().toString().equals("") || tv2.getText().toString().equals("") || tv1.getText()== null || tv2.getText() == null) {
			// RAFAEL System.out.println("entrou false");
			return false;
		} else {
			// RAFAEL System.out.println("entrou true");
			return true;
		}
	}

}
