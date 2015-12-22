package com.example.casa393;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ProcessaXML extends MinhaActivity {

	public void processa(String arquivo) throws Exception {

			if (arquivo == null) {
				erro = "Arquivo é nulo";
				Exception e = new Exception();
				throw (e);
			} else if (!arquivo.contains("<itens>")) {
				erro = arquivo;
				Exception e = new Exception();
				throw (e);
			}

			XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory
					.newInstance();
			XmlPullParser myParser = xmlFactoryObject.newPullParser();

			InputStream stream = new ByteArrayInputStream(arquivo.getBytes());

			// InputStream in = getResources().openRawResource(
			// getResources().getIdentifier("raw/fonte", "raw",
			// getPackageName()));

			myParser.setInput(stream, null);
			myParser.nextTag();
			lista = leArquivo(myParser);

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
}
