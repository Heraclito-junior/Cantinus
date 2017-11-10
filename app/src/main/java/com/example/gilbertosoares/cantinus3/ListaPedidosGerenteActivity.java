package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaPedidosGerenteActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;

    private int idAssociado1 = 0;
    private int idAssociado2 = 0;
    private int idAssociado3 = 0;
    private int idAssociado4 = 0;
    private int idAssociado5 = 0;
    private int idAssociado6 = 0;
    private int idAssociado7 = 0;
    private int idAssociado8 = 0;
    private int idAssociado9 = 0;
    private int idAssociado10 = 0;
    private int idAssociado11 = 0;
    private int idAssociado12 = 0;
    private int idAssociado13 = 0;

    private SQLiteDatabase database;
    SharedPreferences sharedPreferences;
    private String usuario;

    private ArrayList<TextView> listaNomes;
    private ArrayList<Button> listaBotoes;
    private ArrayList<Integer> listaIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos_gerente);

        TableLayout listaOpcoes = (TableLayout) findViewById(R.id.tabelaOpcao);

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        usuario = sharedPreferences.getString("login", "");

        listaNomes = new ArrayList<TextView>();
        listaIDs = new ArrayList<Integer>();

        listaIDs.add(idAssociado1);
        listaIDs.add(idAssociado2);
        listaIDs.add(idAssociado3);
        listaIDs.add(idAssociado4);
        listaIDs.add(idAssociado5);
        listaIDs.add(idAssociado6);
        listaIDs.add(idAssociado7);
        listaIDs.add(idAssociado8);
        listaIDs.add(idAssociado9);
        listaIDs.add(idAssociado10);
        listaIDs.add(idAssociado11);
        listaIDs.add(idAssociado12);
        listaIDs.add(idAssociado13);

        listaBotoes = new ArrayList<Button>();

        button1 = (Button) findViewById(R.id.button1);
        listaBotoes.add(button1);
        button2 = (Button) findViewById(R.id.button2);
        listaBotoes.add(button2);
        button3 = (Button) findViewById(R.id.button3);
        listaBotoes.add(button3);
        button4 = (Button) findViewById(R.id.button4);
        listaBotoes.add(button4);
        button5 = (Button) findViewById(R.id.button5);
        listaBotoes.add(button5);
        button6 = (Button) findViewById(R.id.button6);
        listaBotoes.add(button6);
        button7 = (Button) findViewById(R.id.button7);
        listaBotoes.add(button7);
        button8 = (Button) findViewById(R.id.button8);
        listaBotoes.add(button8);
        button9 = (Button) findViewById(R.id.button9);
        listaBotoes.add(button9);
        button10 = (Button) findViewById(R.id.button10);
        listaBotoes.add(button10);
        button11 = (Button) findViewById(R.id.button11);
        listaBotoes.add(button11);
        button12 = (Button) findViewById(R.id.button12);
        listaBotoes.add(button12);
        button13 = (Button) findViewById(R.id.button13);
        listaBotoes.add(button13);

        povoarPedidos();

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(6);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(8);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(9);
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(10);
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(11);
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(12);
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirPedido(13);
            }
        });
    }

    public void povoarPedidos(){
        String[] columns = {
                "Produto"
        };

        SQLiteDatabase db = database;
        Cursor cursor;

        cursor=db.rawQuery("SELECT * FROM PEDIDO WHERE Status = 'Enviado'", null);

        int cursorCount = cursor.getCount();
        int idPedido;
        String status;
        int auxID = 0;

        if (cursor.moveToFirst()){
            do{
                idPedido = cursor.getInt(cursor.getColumnIndex("ID"));
                status = cursor.getString(cursor.getColumnIndex("Status"));
                listaBotoes.get(auxID).setText("Pedido nº: " + idPedido + "(" + status + ")");
                listaBotoes.get(auxID).setVisibility(View.VISIBLE);
                listaIDs.set(auxID, idPedido);
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

        cursor=db.rawQuery("SELECT * FROM PEDIDO WHERE Status = 'Confirmado'", null);

        if (cursor.moveToFirst()){
            do{
                idPedido = cursor.getInt(cursor.getColumnIndex("ID"));
                status = cursor.getString(cursor.getColumnIndex("Status"));
                listaBotoes.get(auxID).setText("Pedido nº " + idPedido + " (" + status + ")");
                listaBotoes.get(auxID).setVisibility(View.VISIBLE);
                listaIDs.set(auxID, idPedido);
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

        cursor=db.rawQuery("SELECT * FROM PEDIDO WHERE Status = 'Pronto'", null);

        if (cursor.moveToFirst()){
            do{
                idPedido = cursor.getInt(cursor.getColumnIndex("ID"));
                status = cursor.getString(cursor.getColumnIndex("Status"));
                listaBotoes.get(auxID).setText("Pedido nº " + idPedido + " (" + status + ")");
                listaBotoes.get(auxID).setVisibility(View.VISIBLE);
                listaIDs.set(auxID, idPedido);
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

        cursor=db.rawQuery("SELECT * FROM PEDIDO WHERE Status = 'Entregue'", null);

        if (cursor.moveToFirst()){
            do{
                idPedido = cursor.getInt(cursor.getColumnIndex("ID"));
                status = cursor.getString(cursor.getColumnIndex("Status"));
                listaBotoes.get(auxID).setText("Pedido nº " + idPedido + " (" + status + ")");
                listaBotoes.get(auxID).setVisibility(View.VISIBLE);
                listaIDs.set(auxID, idPedido);
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

    }

    public void abrirPedido(int id){

        Intent pedidoGerente = new Intent(ListaPedidosGerenteActivity.this,PedidoGerente.class);
        pedidoGerente.putExtra("IDpedido", listaIDs.get(id-1));

        startActivity(pedidoGerente);

    }

    @Override
    public void onStart(){
        super.onStart();

        povoarPedidos();
    }
}
