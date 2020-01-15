package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private Button btnMostrar;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue= Volley.newRequestQueue(this);
        getDatosVolley();
        /*btnMostrar=(Button) findViewById(R.id.btnToast);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDatosVolley();

            }
        });
        */
    }

    private void getDatosVolley(){
        String url="http://192.168.0.12:80/lopez/public/api/lista/usuarios";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray= response.getJSONArray("usuarios");
                    for (int i = 0 ; i < mJsonArray.length(); i++){
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String nombre= mJsonObject.getString("name");
                        int personal_id= mJsonObject.getInt("personal_id");
                        Toast.makeText(MainActivity.this, "EL NOMBRE ES: "+ nombre+ "PERSONAL ID: "+ personal_id, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

}
