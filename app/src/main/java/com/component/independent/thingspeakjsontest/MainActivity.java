package com.component.independent.thingspeakjsontest;

import android.media.DrmInitData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String json_url = "http://api.thingspeak.com/channels/179406/feeds/last.json?api_key=MQ4X7JSNCON86NA6";

    TextView created_at;
    TextView entry_id;
    TextView field1;
    TextView field2;
    Button button;

    RequestQueue updateled = Volley.newRequestQueue(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        created_at=(TextView)findViewById(R.id.textView2);
        entry_id=(TextView)findViewById(R.id.textView3);
        field1=(TextView)findViewById(R.id.textView4);
        field2 = (TextView)findViewById(R.id.textView5);

        button = (Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET , json_url,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MainActivity.this,"Entered response try",
                                        Toast.LENGTH_LONG).show();
                                if(response==null)
                                {
                                    Toast.makeText(MainActivity.this,"Response is empty",
                                            Toast.LENGTH_LONG).show();
                                }
                                else {

                                    try {
                                        created_at.setText(response.getString("created_at"));
                                        field1.setText(response.getString("field1"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MySingleton.getInstance(MainActivity.this).addToQueue(jsonObjectRequest);
            }
        });

    }
}
