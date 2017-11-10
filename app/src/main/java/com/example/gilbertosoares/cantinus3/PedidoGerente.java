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
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PedidoGerente extends AppCompatActivity {
    private static final String TAG = "CardapioCliente";

    private TextView textNome1;
    private TextView textNome2;
    private TextView textNome3;
    private TextView textNome4;
    private TextView textNome5;
    private TextView textNome6;
    private TextView textNome7;
    private TextView textNome8;
    private TextView textNome9;
    private TextView textNome10;
    private TextView textNome11;
    private TextView textNome12;
    private TextView textNome13;

    SharedPreferences sharedPreferences;
    private SQLiteDatabase database;

    private ArrayList<TextView> listaNomes;

    private TextView textStatus;
    private TextView textTitle;

    private Button buttonEstado;

    private int idPedido;
    private String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_gerente);

        Intent mIntent = getIntent();
        idPedido = mIntent.getIntExtra("IDpedido", 0);

        TableLayout listaOpcoes = (TableLayout) findViewById(R.id.tabelaOpcao);
        textStatus = (TextView) findViewById(R.id.textStatus);
        textTitle = (TextView) findViewById(R.id.textTitle);
        buttonEstado = (Button) findViewById(R.id.buttonEstado);

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        listaNomes = new ArrayList<TextView>();

        textNome1 = (TextView) findViewById(R.id.textNome1);
        listaNomes.add(textNome1);
        textNome2 = (TextView) findViewById(R.id.textNome2);
        listaNomes.add(textNome2);
        textNome3 = (TextView) findViewById(R.id.textNome3);
        listaNomes.add(textNome3);
        textNome4 = (TextView) findViewById(R.id.textNome4);
        listaNomes.add(textNome4);
        textNome5 = (TextView) findViewById(R.id.textNome5);
        listaNomes.add(textNome5);
        textNome6 = (TextView) findViewById(R.id.textNome6);
        listaNomes.add(textNome6);
        textNome7 = (TextView) findViewById(R.id.textNome7);
        listaNomes.add(textNome7);
        textNome8 = (TextView) findViewById(R.id.textNome8);
        listaNomes.add(textNome8);
        textNome9 = (TextView) findViewById(R.id.textNome9);
        listaNomes.add(textNome9);
        textNome10 = (TextView) findViewById(R.id.textNome10);
        listaNomes.add(textNome10);
        textNome11 = (TextView) findViewById(R.id.textNome11);
        listaNomes.add(textNome11);
        textNome12 = (TextView) findViewById(R.id.textNome12);
        listaNomes.add(textNome12);
        textNome13 = (TextView) findViewById(R.id.textNome13);
        listaNomes.add(textNome13);

        povoarPedido();

        buttonEstado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                alterarEstado();
            }
        });

    }

    public void povoarPedido(){

        SQLiteDatabase db = database;
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String usuario = sharedPreferences.getString("login", "");

        Cursor cursor;

        cursor=db.rawQuery("SELECT * FROM PEDIDO WHERE ID = "+idPedido, null);

        if(cursor.moveToFirst()){

            status =  cursor.getString(cursor.getColumnIndex("Status"));
            textStatus.setText("Status: " + status);
            Log.w(TAG,"Status : "+status);
            String clienteID =  cursor.getString(cursor.getColumnIndex("ClienteID"));
            textTitle.setText("Pedido nº: " + idPedido + "\nCliente: " + clienteID);
        }
        cursor.close();

        if(status.equals("Enviado")){
            buttonEstado.setText("Confirmar");
        } else if (status.equals("Confirmado")){
            buttonEstado.setText("Pronto");
        } else if (status.equals("Pronto")){
            buttonEstado.setText("Entregue");
        }else{
            buttonEstado.setText("Entregado");


            //finish();

        }

        String nomeProduto;
        String opcoes;
        int quantidade;
        int idNoCarrinho;
        int auxID = 0;


        cursor=db.rawQuery("SELECT * FROM PEDIDOPRODUTO WHERE PedidoID = "+ idPedido, null);

        if (cursor.moveToFirst()){
            do{
                nomeProduto = cursor.getString(cursor.getColumnIndex("Produto"));
                opcoes = cursor.getString(cursor.getColumnIndex("Opcoes"));
                quantidade = cursor.getInt(cursor.getColumnIndex("QuantidadeProduto"));
                listaNomes.get(auxID).setVisibility(View.VISIBLE);
                if(!opcoes.equals("")) {
                    listaNomes.get(auxID).setText(nomeProduto + " (" + quantidade + ") – " + opcoes);
                } else {
                    listaNomes.get(auxID).setText(nomeProduto + " (" + quantidade + ")");
                }
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

    }

    public void alterarEstado(){
        SQLiteDatabase db = database;

        if(status.equals("Enviado")){
            String strSQL = "UPDATE PEDIDO SET Status = 'Confirmado' WHERE ID = "+ idPedido;

            database.execSQL(strSQL);
        } else if (status.equals("Confirmado")){
            String strSQL = "UPDATE PEDIDO SET Status = 'Pronto' WHERE ID = "+ idPedido;

            database.execSQL(strSQL);
        } else if (status.equals("Pronto")){
            String strSQL = "UPDATE PEDIDO SET Status = 'Entregue' WHERE ID = "+ idPedido;

            database.execSQL(strSQL);

        }
        finish();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        povoarPedido();
    }
}
