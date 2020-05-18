package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerThread extends Thread {
    boolean keepGoing = true;
    int port;

    double valUSD;
    double valEUR;

    public ServerThread(int port) {
        this.port = port;
    }

    public void run() {
        while(keepGoing) {
            // TODO ...
            BitcoinAsyncTask task = new BitcoinAsyncTask(this.port, this);
            task.execute("");
            try {
                sleep(60000);
            } catch (Exception ex) {
                Log.e(Constants.TAG, ex.getMessage());
            }

        }
    }

    public void ParseResult(String result) {
        try {
            JSONObject jObject = new JSONObject(result);
            JSONObject joBPI = jObject.getJSONObject("bpi");
            JSONObject joUSD = joBPI.getJSONObject("USD");
            this.valUSD = joUSD.getDouble("rate_float");
            JSONObject joEUR = joBPI.getJSONObject("EUR");
            this.valEUR = joEUR.getDouble("rate_float");
        } catch (Exception ex) {
            Log.e(Constants.TAG, ex.getMessage());
        }
    }

    public double GetValueInCurrency(String currency) {
        if(currency == "EUR") {
            return this.valEUR;
        } else {
            return this.valUSD;
        }
    }
}
