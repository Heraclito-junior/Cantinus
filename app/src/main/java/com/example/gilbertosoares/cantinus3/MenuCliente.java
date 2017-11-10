package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuCliente extends AppCompatActivity {

    double largura;
    double altura;

    SharedPreferences sharedPreferences;

    private TextView mTextTitle;

    private TextView mTextSaldo;

    private String usuario;

    // Banco de Dados
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cliente);

        Button addProduto = (Button) findViewById(R.id.buttonAddProduto);
        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirecionarCardapio();
            }
        });

        Button acompanharProduto = (Button) findViewById(R.id.buttonAcompanhar);
        acompanharProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listaPedidosCliente = new Intent(MenuCliente.this,ListaPedidosClienteActivity.class);

                startActivity(listaPedidosCliente);
            }
        });

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        mTextTitle = (TextView) findViewById(R.id.greetClient);
        mTextSaldo = (TextView) findViewById(R.id.textSaldo);

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("login", "");
        mTextTitle.setText(mTextTitle.getText() + " " + result);
        usuario = result;

        double saldo = getCredito();
        String textoSaldo = "Saldo: R$ " + saldo;
        textoSaldo = textoSaldo.replace('.', ',');
        mTextSaldo.setText(textoSaldo);
    }


    public void redirecionarCardapio(){

        Intent clienteCardapio = new Intent(MenuCliente.this,CardapioCliente.class);

        startActivity(clienteCardapio);

    }

    public double getCredito() {

        String[] columns = {
                "Credito"
        };
        SQLiteDatabase db = database;

        String selection = "Matricula = ?";

        String[] selectionArgs = {usuario};

        Cursor cursor = db.query("Creditos", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        if (cursor != null) {
            cursor.moveToFirst();
            String retorno = cursor.getString(cursor.getColumnIndex("Credito"));
            cursor.close();
            return Double.parseDouble(retorno);
        }
        return -1;
    }

    @Override
    public void onStart(){
        super.onStart();

        double saldo = getCredito();
        String textoSaldo = "Saldo: R$ " + saldo;
        textoSaldo = textoSaldo.replace('.', ',');
        mTextSaldo.setText(textoSaldo);
    }

}
