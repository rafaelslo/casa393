package com.example.casa393;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;

public class Comunica extends AsyncTask<String, Integer, String> {
	Context ctx;
	Exception exception;
	public Comunica(Context ctx) {
		this.ctx = ctx;
	}

	protected String doInBackground(String... urls) {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			URI website = new URI(urls[0]);
			request.setURI(website);

			HttpResponse response = httpclient.execute(request);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line;
			StringBuilder total = new StringBuilder();
			while ((line = in.readLine()) != null) {
			    total.append(line);
			}
			
			return total.toString();
			
		} catch (Exception e) {
			this.exception = e;
			return null;
		}
	}

	protected void onProgressUpdate(Integer... progress) {
		// setProgressPercent(progress[0]);
	}

	protected void onPostExecute(String result) {
		// showDialog("Downloaded " + result + " bytes");
	}

}
