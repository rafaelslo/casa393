package com.example.casa393;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ItemSensor {

	private String id;
	private String descricao;
	private String local;
	private String pavimento;
	private String sistema;
	
	
	public ItemSensor(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "item");

	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	            this.id = leTag(parser,"id");
	        } else if (name.equals("descricao")) {
	            this.descricao = leTag(parser,"descricao");
	        } else if (name.equals("sistema")) {
	            this.sistema = leTag(parser,"sistema");
	        } else if (name.equals("local")) {
	            this.local = leTag(parser,"local");
	        } else if (name.equals("pavimento")) {
	            this.pavimento = leTag(parser,"pavimento");
	        } else {
	            skip(parser);
	        }
	    }		
		
	}
	
	private String leTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
	    String result = "";
	    parser.require(XmlPullParser.START_TAG, null, tag);
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    parser.require(XmlPullParser.END_TAG, null, tag);
    	//System.out.println("Achou: " + tag + " result: " + result);
	    return result;
	}

	 public String toString() {  
		  
		    return this.local;  
	 }
	
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }	
	
	// GETTERS AND SETTERS
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getPavimento() {
		return pavimento;
	}
	public void setPavimento(String pavimento) {
		this.pavimento = pavimento;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	
}
