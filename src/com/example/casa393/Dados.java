package com.example.casa393;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Dados {
	Context ctx;
	
	public Dados(Context ctx) {
		this.ctx = ctx;
	}
	
	public void salvarDados(String servidor, String senha) {
		// Cria ou abre.
		SharedPreferences prefs = ctx.getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);
 
		// Precisamos utilizar um editor para alterar Shared Preferences.
		Editor ed = prefs.edit();
 
		// salvando informações de acordo com o tipo
		ed.putString("SERVIDOR", servidor);
		ed.putString("SENHA", senha);
 
		// Grava efetivamente as alterações.
		ed.commit();		
	}

	public String getDados(String chave) {
		// Acesso às Shared Preferences usando o nome definido.
		SharedPreferences prefs = ctx.getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);
		String texto = prefs.getString(chave, "");
		return texto;		
	}
	
	
}
