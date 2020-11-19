package com.example.cadastrocliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrocliente.Dao.ClienteDao;
import com.example.cadastrocliente.model.Cliente;

public class FormularioCliente extends AppCompatActivity {

    EditText txtNome, txtCpf, txtEmail, txtTelefone, txtEndereco, txtSenha;
    Button btnModificar;
    Cliente editarCliente, cliente;
    ClienteDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        cliente = new Cliente();
        dao = new ClienteDao(FormularioCliente.this);

        Intent intent = getIntent();
        editarCliente = (Cliente) intent.getSerializableExtra("escolhido");

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtCpf = (EditText) findViewById(R.id.txtCpf);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtSenha = (EditText) findViewById(R.id.txtSenha);


        btnModificar = (Button) findViewById(R.id.btnModificar);

       if(editarCliente != null){
           btnModificar.setText("Modificar Cliente");

           txtNome.setText(editarCliente.getNome());
           txtCpf.setText(editarCliente.getCpf());
           txtEmail.setText(editarCliente.getEmail());
           txtTelefone.setText(editarCliente.getTelefone());
           txtEndereco.setText(editarCliente.getEndereco());
           txtSenha.setText(editarCliente.getSenha()+"");

           cliente.setID(editarCliente.getID());

       }else{
           btnModificar.setText("Cadastrar novo Cliente");
       }
       btnModificar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cliente.setNome(txtNome.getText().toString());
               cliente.setCpf(txtCpf.getText().toString());
               cliente.setEmail(txtEmail.getText().toString());
               cliente.setTelefone(txtTelefone.getText().toString());
               cliente.setEndereco(txtEndereco.getText().toString());
               cliente.setSenha(Integer.parseInt(txtSenha.getText().toString()));

               if(btnModificar.getText().toString().equals("Cadastrar")){
                   dao.salvarCliente(cliente);
                   dao.close();
               }else{
                   dao.atualizar(cliente);
                   dao.close();
               }
           }
        }) ;

   }
}