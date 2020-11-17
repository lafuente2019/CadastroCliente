package com.example.cadastrocliente.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cadastrocliente.model.Cliente;

public class ClienteDao extends SQLiteOpenHelper {

    private static final String DATABASE = "muchachos";
    private static final int VERSION = 1;

    public ClienteDao (Context context){
        super(context, DATABASE, null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String cliente = "CREATE TABLE clientes(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, cpf TEXT NOT NULL," +
                "email TEXT NOT NULL, telefone TEXT NOT NULL, endereco TEXT NOT NULL, senha INTEGER NOT NULL) ";

        db.execSQL(cliente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String cliente = "DROP TABLE IF EXISTS clientes";
        db.execSQL(cliente);
    }

    //Metodo salvar
    public void salvarCliente(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put("nome", cliente.getNome());
        values.put("cpf", cliente.getCpf());
        values.put("email", cliente.getEmail());
        values.put("telefone", cliente.getTelefone());
        values.put("endereco", cliente.getEndereco());
        values.put("senha", cliente.getSenha());


        getWritableDatabase().insert("clientes",null,values);
    }

    //Metodo Atualizar
    public void atualizar(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put("nome", cliente.getNome());
        values.put("cpf", cliente.getCpf());
        values.put("email", cliente.getEmail());
        values.put("telefone", cliente.getTelefone());
        values.put("endereco", cliente.getEndereco());
        values.put("senha", cliente.getSenha());

        String [] args = {cliente.getID().toString()};
        getWritableDatabase().update("clientes",values, "id=?", args);

    }




    //Metodo listar cliente
    public ArrayList<Cliente> getList(){
        String [] columns = {"ID", "nome", "cpf", "email", "telefone", "endereco", "senha"};
        Cursor cursor = getWritableDatabase().query("clientes", columns, null,null,null,null,null,null);
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        while (cursor.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setID(cursor.getLong(0));
            cliente.setNome(cursor.getString(1));
            cliente.setCpf(cursor.getString(2));
            cliente.setEmail(cursor.getString(3));
            cliente.setTelefone(cursor.getString(4));
            cliente.setEndereco(cursor.getString(5));
            cliente.setSenha(cursor.getInt( 6));

            clientes.add(cliente);


        }
        return clientes;
    }
}
