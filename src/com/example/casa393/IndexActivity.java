package com.example.casa393;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class IndexActivity extends MinhaActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Intent intent;
	    
		//Verifica dados da configuração 
		Dados dados = new Dados(getApplicationContext());
		servidor = dados.getDados("SERVIDOR");
		senha = dados.getDados("SENHA");
		
			if(servidor.toString().equals("") || senha.toString().equals("")) {
				Toast.makeText(getApplicationContext(),"Dados de conexão não preenchidos", Toast.LENGTH_LONG).show();
				System.out.println("configura");
				intent = new Intent(this, ConfiguraActivity.class);
			} else {
				System.out.println("principal");
			    intent = new Intent(this, MainActivity.class);
			}	    
			startActivity(intent);
			finish();
	}
}
