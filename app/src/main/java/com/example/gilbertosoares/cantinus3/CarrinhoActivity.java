package com.example.gilbertosoares.cantinus3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CarrinhoActivity extends AppCompatActivity {

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

    private ArrayList<TextView> listaNomes;
    private ArrayList<Button> listaBotoes;
    private ArrayList<Integer> listaIDs;

    Double soma=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        TableLayout listaOpcoes = (TableLayout) findViewById(R.id.tabelaOpcao);

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        listaNomes = new ArrayList<TextView>();
        listaIDs = new ArrayList<Integer>();

        textNome1 = (TextView) findViewById(R.id.textNome1);
        listaNomes.add(textNome1);
        listaIDs.add(idAssociado1);
        textNome2 = (TextView) findViewById(R.id.textNome2);
        listaNomes.add(textNome2);
        listaIDs.add(idAssociado2);
        textNome3 = (TextView) findViewById(R.id.textNome3);
        listaNomes.add(textNome3);
        listaIDs.add(idAssociado3);
        textNome4 = (TextView) findViewById(R.id.textNome4);
        listaNomes.add(textNome4);
        listaIDs.add(idAssociado4);
        textNome5 = (TextView) findViewById(R.id.textNome5);
        listaNomes.add(textNome5);
        listaIDs.add(idAssociado5);
        textNome6 = (TextView) findViewById(R.id.textNome6);
        listaNomes.add(textNome6);
        listaIDs.add(idAssociado6);
        textNome7 = (TextView) findViewById(R.id.textNome7);
        listaNomes.add(textNome7);
        listaIDs.add(idAssociado7);
        textNome8 = (TextView) findViewById(R.id.textNome8);
        listaNomes.add(textNome8);
        listaIDs.add(idAssociado8);
        textNome9 = (TextView) findViewById(R.id.textNome9);
        listaNomes.add(textNome9);
        listaIDs.add(idAssociado9);
        textNome10 = (TextView) findViewById(R.id.textNome10);
        listaNomes.add(textNome10);
        listaIDs.add(idAssociado10);
        textNome11 = (TextView) findViewById(R.id.textNome11);
        listaNomes.add(textNome11);
        listaIDs.add(idAssociado11);
        textNome12 = (TextView) findViewById(R.id.textNome12);
        listaNomes.add(textNome12);
        listaIDs.add(idAssociado12);
        textNome13 = (TextView) findViewById(R.id.textNome13);
        listaNomes.add(textNome13);
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

        povoarCarrinho();

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(1);
                atualizarCredito();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(2);
                atualizarCredito();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(3);
                atualizarCredito();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(4);
                atualizarCredito();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(5);
                atualizarCredito();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(6);
                atualizarCredito();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(7);
                atualizarCredito();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(8);
                atualizarCredito();

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(9);
                atualizarCredito();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(10);
                atualizarCredito();
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(11);
                atualizarCredito();
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(12);
                atualizarCredito();
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removerDoCarrinho(13);
                atualizarCredito();
            }
        });


        atualizarCredito();




    }

    public void atualizarCredito(){

        TextView  valor = (TextView) findViewById(R.id.textValorCar);


        soma=0.0;
        Double auxiliarSoma;


        SQLiteDatabase db = database;
        Cursor cursor;
        int idsAtual=0;

        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        idsAtual = cursor.getCount();

        if(idsAtual==0){
            valor.setText("Total : 0");
            //return;
        }else{
            if (cursor.moveToFirst()){
                do{
                    auxiliarSoma = cursor.getDouble(cursor.getColumnIndex("PrecoTotal"));
                    soma+=auxiliarSoma;
                    //listaBoxes.get(auxID).setVisibility(View.VISIBLE);
                    //listaBoxes.get(auxID).setText(sabor);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }

        double finalValue = Math.round( soma * 100.0 ) / 100.0;

        valor.setText("Total : "+finalValue);


    }

    public void povoarCarrinho(){
        String[] columns = {
                "Produto"
        };

        SQLiteDatabase db = database;
        Cursor cursor;

        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        int cursorCount = cursor.getCount();
        String nomeProduto;
        String opcoes;
        int quantidade;
        int idNoCarrinho;
        int auxID = 0;

        if (cursor.moveToFirst()){
            do{
                nomeProduto = cursor.getString(cursor.getColumnIndex("Produto"));
                opcoes = cursor.getString(cursor.getColumnIndex("Opcoes"));
                quantidade = cursor.getInt(cursor.getColumnIndex("QuantidadeProduto"));
                idNoCarrinho = cursor.getInt(cursor.getColumnIndex("ID"));
                listaNomes.get(auxID).setVisibility(View.VISIBLE);
                listaBotoes.get(auxID).setVisibility(View.VISIBLE);
                listaNomes.get(auxID).setText(nomeProduto + " (" + quantidade + ") – " + opcoes);
                listaIDs.set(auxID, idNoCarrinho);
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

    }

    public void removerDoCarrinho(int id){

        SQLiteDatabase db = database;
        Cursor cursor;

        db.delete("CARRINHO","ID=?",new String[]{"" + listaIDs.get(id-1)});

        int cont = 0;

        for(int aux : listaIDs){

            listaNomes.get(cont).setVisibility(View.INVISIBLE);
            listaBotoes.get(cont).setVisibility(View.INVISIBLE);
            cont++;
        }

        povoarCarrinho();

    }
}
