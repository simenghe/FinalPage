package com.example.simen.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView txtURL;
    private TextView urlText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Show the text from the web using a text view
        txtURL=(TextView)findViewById(R.id.txtURL);
        urlText=(TextView)findViewById(R.id.urlText);

        //Initiate the buttons needed for the controls.
        Button btnRight = (Button) findViewById(R.id.btnRight);
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnUp = (Button) findViewById(R.id.btnUp);
        Button btnDown = (Button) findViewById(R.id.btnDown);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        Button btnIP=(Button)findViewById(R.id.btnIP);
        Button btnActivity2=(Button)findViewById(R.id.btnActivity2);
        //Show the IP that the user entered.
        TextView viewIP=(TextView)findViewById(R.id.viewIP);
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent=new Intent(getApplicationContext(),SecondActivity.class);
                startIntent.putExtra("BigCane","Initiated");
                startActivity(startIntent);
            }
        });
        btnIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtIP=(EditText) findViewById(R.id.txtIP);
                String userIP=txtIP.getText().toString();
                TextView viewIP=(TextView)findViewById(R.id.viewIP);
                viewIP.setText(userIP);
            }
        });
        //Set toggles
        btnRight.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch ( motionEvent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        //new JSONTask().execute("http://10.145.144.153:1234/left");
                        break;
                    case MotionEvent.ACTION_UP:
                        //new JSONTask().execute("http://10.145.144.153:1234/left");
                        break;
                }
                return true;
            }
        });
        btnLeft.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch ( motionEvent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        //new JSONTask(----).execute("http://10.145.144.153:1234/left");
                        break;
                    case MotionEvent.ACTION_UP:
                        //new JSONTask().execute("http://10.145.144.153:1234/left");
                        break;
                }
                return true;
            }
        });
        btnUp.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //TextView notification=(TextView)findViewById(R.id.txtURL);
                switch ( motionEvent.getAction() ) {

                    case MotionEvent.ACTION_DOWN:
                        new JSONTask().execute("http://192.168.0.107:5000/left");
                        //notification.setText("Arrow up is HELD DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        new JSONTask().execute("http://192.168.0.107:5000/stop");
                        //notification.setText("Arrow up is RELEASED.");
                        break;
                }
                return true;
            }
        });
        btnDown.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch ( motionEvent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        new JSONTask().execute("http://169.254.115.228:1234/left");
                        break;
                    case MotionEvent.ACTION_UP:
                        new JSONTask().execute("http://169.254.115.228:1234/stop");
                        break;
                }
                return true;
            }
        });
    }
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "");
                }

                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();    
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            urlText.setText(result);
        }
    }
}
