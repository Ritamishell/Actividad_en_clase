package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.listview.Adaptadores.Adaptadordeusuario;
import com.example.listview.Modelo.Usuario;
import com.example.listview.Webservice.Asynchtask;
import com.example.listview.Webservice.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    ListView lstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstOpciones = (ListView)findViewById(R.id.lstusuario);
        View header = getLayoutInflater().inflate(R.layout.lyheaderusuario, null);
        lstOpciones.addHeaderView(header);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject JSONlista = new JSONObject(result);
        JSONArray JSONlistaUsuario= JSONlista.getJSONArray("data");
        ArrayList< Usuario >lstUsuarios=Usuario.JsonObjectsBuild(JSONlistaUsuario);
        Adaptadordeusuario adapatorUsuario = new Adaptadordeusuario(this, lstUsuarios);
        lstOpciones.setAdapter(adapatorUsuario);

    }
}