package com.example.casa393;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;

public class Conecta extends MinhaActivity {
	private String arquivo;
	private String website;
	public static ArrayList<ItemSensor> listaTemp;

	public Conecta(String website) throws Excecao {
		this.website = website;
		System.out.print("website: ");
		System.out.println(website);

		pegaArquivo();
		processaXML();
		atualizaLista();

	}

	private void pegaArquivo() throws Excecao {
		// Obtem o XML do site
		AsyncTask<String, Integer, String> com = new Comunica().execute(website);

		try {
			arquivo = com.get(5000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new Excecao("Interrup��o na execu��o do programa.");
		} catch (ExecutionException e) {
			throw new Excecao("Erro na execu��o do programa.");
		} catch (TimeoutException e) {
			throw new Excecao("TIMEOUT: n�o foi poss�vel conectar.");
		}

	}

	public void processaXML() throws Excecao {

		if (arquivo == null) {
			throw new Excecao("Arquivo � nulo");
		} else if (!arquivo.contains("<itens>")) {
			throw new Excecao(arquivo);
		}

		XmlPullParserFactory xmlFactoryObject;
		try {
			xmlFactoryObject = XmlPullParserFactory.newInstance();
			XmlPullParser myParser = xmlFactoryObject.newPullParser();
			InputStream stream = new ByteArrayInputStream(arquivo.getBytes());

			// InputStream in = getResources().openRawResource(
			// getResources().getIdentifier("raw/fonte", "raw",
			// getPackageName()));

			myParser.setInput(stream, null);
			myParser.nextTag();
			listaTemp = leArquivo(myParser);

		} catch (XmlPullParserException e) {
			throw new Excecao("XmlPullParserException");
		} catch (IOException e) {
			throw new Excecao("IOException");
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<ItemSensor> leArquivo(XmlPullParser parser)
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

	private void atualizaLista() {
		if(listaTemp.size()>1) {
			lista = listaTemp;
		} else {
			ItemSensor novoItem = listaTemp.get(0);
			for (ItemSensor item : lista) {
				if(item.getId().equals(novoItem.getId())) {
					item.setValor(novoItem.getValor());
					break;
				}
			}
		}
	}

}
