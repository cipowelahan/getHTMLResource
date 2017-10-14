package com.example.cipowela.gethtmlresource;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cipowela on 14/10/17.
 */

public class GetHTMLSource extends AsyncTask<String,Void,String> {

    private TextView result;

    public GetHTMLSource(TextView result) {
        this.result = result;
    }

    @Override
    protected String doInBackground(String... params) {
        String link = params[0];
        InputStream in;

        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            in = connection.getInputStream();

            BufferedReader buff = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = buff.readLine()) != null) {
                sb.append(line + "\n");
            }

            buff.close();
            in.close();

            return sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        result.setText(s);
    }
}
