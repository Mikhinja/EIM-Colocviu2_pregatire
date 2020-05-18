package ro.pub.cs.systems.eim.practicaltest02;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class BitcoinAsyncTask extends AsyncTask<String, Void, String> {
    int port;
    String currency;
    public BitcoinAsyncTask(int port, String currency) {
        this.port = port;
        this.currency = currency;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(Constants.URL);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            return httpClient.execute(httpGet, responseHandler);
        } catch (ClientProtocolException clientProtocolException) {
            Log.e(Constants.TAG, clientProtocolException.getMessage());
        } catch (IOException ioException) {
            Log.e(Constants.TAG, ioException.getMessage());
        }

        return null;
    }
    @Override
    public void onPostExecute(String content) {
        //String prevText = tvOutDisplay.getText().toString();
        Log.d(Constants.TAG, "Got response: " + content);
        //tvOutDisplay.setText(prevText + "\n" + content);
    }
}
