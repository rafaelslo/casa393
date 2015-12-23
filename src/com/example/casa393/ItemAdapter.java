package com.example.casa393;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ItemAdapter extends BaseAdapter {
	
	Context ctx;
    ArrayList<ItemSensor> listaAplicada;    
	
	public ItemAdapter(Context ctx, ArrayList<ItemSensor> listaAplicada) {
		super();
		this.listaAplicada = listaAplicada;
		this.ctx = ctx;
	}

	@Override
	public int getViewTypeCount() {
		return 4;
	}

	@Override
	public int getItemViewType(int position) {
		String tipo = listaAplicada.get(position).getSistema();
		int retorna=0;
		// RAFAEL System.out.println("tipo: " + tipo);
		
		if(tipo.equals("Luz Simples")) {
			retorna=0;
		} else if (tipo.equals("Temperatura")) {
			retorna=1;
		} else if (tipo.equals("Presenca")) {
			retorna=2;
		} else if (tipo.equals("Energia")) {
			retorna=3;
		}
		// RAFAEL System.out.println("passou: "+retorna);
		return retorna;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		int type = getItemViewType(position);
		// RAFAEL System.out.println("getView");

		if (v == null) { 
			ItemSensor c = listaAplicada.get(position);
			TextView tv1;
			TextView tv2;
			
			if (type == 0) { 
				//ImageView img = (ImageView)	v.findViewById(R.id.img); 
				//img.setImageResource(c.imageId); 
			}
			
			// Inflate the layout according to the view type
			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (type == 0) {
				v = inflater.inflate(R.layout.luz_simples_layout, parent, false); 
				tv1 = (TextView) v.findViewById(R.id.textView1);
				tv2 = (TextView) v.findViewById(R.id.textView2);
				ToggleButton tb1 = (ToggleButton) v.findViewById(R.id.toggleButton1);
				tv1.setText(c.getLocal()); 
				tv2.setText(c.getDescricao());
				tb1.setTag(c.getId());
			} else if (type == 1) {
				v = inflater.inflate(R.layout.temperatura_layout, parent, false); 
				tv1 = (TextView) v.findViewById(R.id.textView1);
				tv2 = (TextView) v.findViewById(R.id.textView2);
				tv2.setText(c.getLocal()); 
				tv1.setText(c.getValor());
				tv1.setTag(c.getId());
			} else if (type == 2) {
				v = inflater.inflate(R.layout.presenca_layout, parent, false); 
				tv1 = (TextView) v.findViewById(R.id.textView1);
				tv2 = (TextView) v.findViewById(R.id.textView2);
				tv2.setText(c.getLocal()); 
				tv1.setText(c.getValor());
				tv1.setTag(c.getId());
			} else if (type == 3) {
				v = inflater.inflate(R.layout.energia_layout, parent, false); 
				tv1 = (TextView) v.findViewById(R.id.textView1);
				tv2 = (TextView) v.findViewById(R.id.textView2);
				tv1.setText(c.getLocal()); 
				tv1.setTag(c.getId());
				tv2.setText(c.getDescricao());
			} else { 
				//v =inflater.inflate(R.layout.simple_contact_layout, parent, false); 
			} 
			System.out.println("----------- ItemSensor------");
			System.out.println(c.toString());
		}
		
		return v;
	}

	@Override
	public int getCount() {
		// RAFAEL System.out.println("------Achou :" + listaAplicada.size());
		return listaAplicada.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
