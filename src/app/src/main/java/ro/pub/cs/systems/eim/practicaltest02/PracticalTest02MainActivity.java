package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;

import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class PracticalTest02MainActivity extends AppCompatActivity {

    private DisplayResultsButtonClickListener displayResultButtonClickListener = new DisplayResultsButtonClickListener();
    private class DisplayResultsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String strPort = etPort.getText().toString();
            int port = Integer.parseInt(strPort);
            BitcoinAsyncTask task = new BitcoinAsyncTask(port, strCurrency);
            task.execute("");
        }
    }

    RadioButtonGroupListener radioButtonGroupListener = new RadioButtonGroupListener();
    private class RadioButtonGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup view, int resId) {
            int radioButtonID = view.getCheckedRadioButtonId();
            View radioButton = view.findViewById(radioButtonID);
            int idx = view.indexOfChild(radioButton);
            if (idx == 0) {
                strCurrency = "USD";
            } else {
                strCurrency = "EUR";
            }
        }
    }

    Button btnStartServer;
    Button btnGetResult;
    EditText etPort;
    TextView tvResult;
    RadioGroup radioGroup;
    String strCurrency = "USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        btnStartServer = (Button)findViewById(R.id.button1);
        if (btnStartServer != null) {
            btnStartServer.setOnClickListener(displayResultButtonClickListener);
        }
        btnGetResult = (Button)findViewById(R.id.buttonResults);

        radioGroup = (RadioGroup)findViewById((R.id.radioGroup));
        radioGroup.setOnCheckedChangeListener(radioButtonGroupListener);

        tvResult = (TextView)findViewById(R.id.textViewResult);
        etPort = (EditText)findViewById(R.id.editPort);
    }
}
