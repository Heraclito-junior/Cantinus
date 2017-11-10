package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GerenteActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private TextView mTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextTitle = (TextView) findViewById(R.id.greetClient);

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("login", "");
        mTextTitle.setText(mTextTitle.getText() + " " + result);

        Button buttonAddCredito = (Button) findViewById(R.id.buttonAddCredito);
        buttonAddCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GerenteActivity.this, CreditoActivity.class);
                startActivity(intent);
            }
        });

        Button buttonAddProduto = (Button) findViewById(R.id.buttonAddProduto);
        buttonAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GerenteActivity.this, AddProdutoActivity.class);
                startActivity(intent);
            }
        });
        Button buttonPedidos = (Button) findViewById(R.id.buttonPedidos);
        buttonPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GerenteActivity.this, ListaPedidosGerenteActivity.class);
                startActivity(intent);
            }
        });
    }

}
