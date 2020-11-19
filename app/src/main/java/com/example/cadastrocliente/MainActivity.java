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

    ListView lista;
    ClienteDao dao;
    ArrayList<Cliente> listCliente;
    Cliente cliente;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Cadastar = (Button) findViewById(R.id.btnCadastrar);
        btn_Cadastar.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioCliente.class);
                startActivity(intent);

            }
        });

        lista = (ListView) findViewById(R.id.listCliente);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Cliente esolhido = (Cliente) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormularioCliente.class);
                i.putExtra("escolhido", esolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                cliente = (Cliente) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar esse Cliente");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dao = new ClienteDao(MainActivity.this);
                dao.excluir(cliente);
                dao.close();
                carregarCliente();
                return true;
            }
        });
    }

    protected  void onResume(){
        super.onResume();
        carregarCliente();
    }

    public  void carregarCliente(){
        dao = new ClienteDao(MainActivity.this);
        listCliente = dao.getList();
        dao.close();

        if(listCliente != null){
            adapter = new ArrayAdapter<Cliente>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, listCliente);
            lista.setAdapter(adapter);
        }

    }
}