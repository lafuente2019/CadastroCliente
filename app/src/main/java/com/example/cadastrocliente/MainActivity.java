package com.example.cadastrocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cadastrocliente.Dao.ClienteDao;
import com.example.cadastrocliente.model.Cliente;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ClienteDao dao;
    Cliente cliente;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}