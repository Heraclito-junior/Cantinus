package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardapioCliente extends AppCompatActivity {


    private CardapioAdapter cardapioLista;
    ArrayList<ItemCardapio> cardap = new ArrayList<ItemCardapio>();


    SharedPreferences sharedPreferences;
    private SQLiteDatabase database;

    private static final String TAG = "CardapioCliente";
    ArrayList<ItemCardapio> MenuCardapio = new ArrayList<ItemCardapio> ();
    TextView valorCarrinho;

    private Button buttonCarrinho;
    private Button buttonFinalizar;

    private Double Total;

    private String usuario;
    Double credito=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_cliente);


        ListView lista = (ListView) findViewById(R.id.listCardapio);

        valorCarrinho = (TextView)  findViewById(R.id.valorTotal);

        System.out.println(lista);

        cardap = new ArrayList<ItemCardapio>();

        cardapioLista = new CardapioAdapter(this, cardap);

        lista.setAdapter(cardapioLista);


        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();



        String[] columns = {
                "Nome","Preco","Descricao"
        };


        SQLiteDatabase db = database;
        Cursor cursor;
        cursor=db.rawQuery("SELECT * FROM PRODUTOS",null);

        int cursorCount = cursor.getCount();
        String Nome;
        String Descricao;
        String id;
        double preco;
        int tempConvertId;
        //ItemCardapio itemTemporario;
        if (cursor.moveToFirst()){
            do{
                Nome = cursor.getString(cursor.getColumnIndex("Nome"));
                preco = Double.parseDouble(cursor.getString(cursor.getColumnIndex("Preco")));
                Descricao = cursor.getString(cursor.getColumnIndex("Descricao"));
                id = cursor.getString(cursor.getColumnIndex("ID"));

                //itemTemporario=new ItemCardapio(Nome,Descricao,3.5,1);
                tempConvertId = Integer.parseInt(id);

                MenuCardapio.add(new ItemCardapio(Nome,Descricao,preco,tempConvertId));
                //ItemCardapio item1= new ItemCardapio("tapioca","deliciosa tapioca com um miriar de opções",3.5,1);

                // do what ever you want here
                Log.w(TAG, "Nome=" + Nome);

            }while(cursor.moveToNext());
        }
        cursor.close();

        buttonCarrinho = (Button) findViewById(R.id.buttonCarrinho);


        buttonCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardapioCliente.this, CarrinhoActivity.class);
                startActivity(intent);
            }
        });

        buttonFinalizar = (Button) findViewById(R.id.buttonFinalizar);

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarPedido();

            }
        });

        Log.w(TAG, "cursorCount=" + cursorCount);

        System.out.println("222222222222222222222222222222");
        System.out.println(database.getMaximumSize());


        //ItemCardapio item1= new ItemCardapio("tapioca","deliciosa tapioca com um miriar de opções",3.5,1);
        // ItemCardapio item2= new ItemCardapio("tapioca","deliciosa tapioca com um miriar de opções",3.5,2);

        for(int i=0;i<MenuCardapio.size();i++){
            cardap.add(MenuCardapio.get(i));
        }



        cardapioLista.notifyDataSetChanged();



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {

                ItemCardapio comidaSelecionada = cardap.get(position);

                Intent acao = new Intent(CardapioCliente.this,
                        detalhesCardapio.class);
                /*if(comidaSelecionada.tipoCardapio==1) {
                    acao.putExtra("ItemEscolhido", 1);
                }else if(comidaSelecionada.tipoCardapio==2){
                    acao.putExtra("ItemEscolhido", 2);
                }*/

                acao.putExtra("ItemEscolhido", comidaSelecionada.tipoCardapio);
                //acao.putExtra(Tweet.TWEET_INFO, tweetSelecionado);

                startActivity(acao);

            }
        });





    }

    @Override
    public void onStart(){
        super.onStart();



        Total=0.0;
        Double auxiliarSoma;


        SQLiteDatabase db = database;
        Cursor cursor;
        int idsAtual=0;

        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        idsAtual = cursor.getCount();

        if(idsAtual==0){
            valorCarrinho.setText("Total : 0");
            //return;
        }else{
            if (cursor.moveToFirst()){
                do{
                    auxiliarSoma = cursor.getDouble(cursor.getColumnIndex("PrecoTotal"));
                    Total+=auxiliarSoma;
                    Log.w(TAG,"teste "+Total);
                    //listaBoxes.get(auxID).setVisibility(View.VISIBLE);
                    //listaBoxes.get(auxID).setText(sabor);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }

        double finalValue = Math.round( Total * 100.0 ) / 100.0;

        valorCarrinho.setText("Total : "+finalValue);


        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("login", "");

        usuario = result;


        String[] columns = {
                "Credito"
        };
        db = database;

        String selection = "Matricula = ?";

        String[] selectionArgs = {usuario};

         cursor = db.query("Creditos", //Table to query
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
            credito=Double.parseDouble(retorno);
            //return
        }else{
            credito=0.0;

        }
        String auxiliarParser=String.valueOf(credito);
        String auxiliarParser2="Credito : "+auxiliarParser;
        TextView cred = (TextView) findViewById(R.id.textCredito);
        cred.setText(auxiliarParser2);







    };

    public void finalizarPedido(){



        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String usuario = sharedPreferences.getString("login", "");

        SQLiteDatabase db = database;

        //antes de tudo, ver se o cliente tem dinheiro

        Cursor cursor;
        cursor = db.rawQuery("SELECT Credito FROM CREDITOS WHERE Matricula = " + Integer.parseInt(usuario), null);

        cursor.moveToFirst();
        double creditoUsuario = cursor.getDouble(cursor.getColumnIndex("Credito"));
        cursor.close();
        if(Total > creditoUsuario){
            Context context = getApplicationContext();

            String text = "Créditos insuficientes!\nVocê possui RS$ "+creditoUsuario +
                    " no sistema e tentou finalizar um pedido de RS$" + Total;
            int duration = Toast.LENGTH_LONG ;

            for (int i=0; i < 3; i++) {
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            return;
        }

        cursor = db.rawQuery("SELECT Contador FROM IDENTIFICADORES WHERE NOME = 'PEDIDO'",null);

        cursor.moveToFirst();
        int contadorAtual = cursor.getInt(cursor.getColumnIndex("Contador"));
        cursor.close();

        int novoContador = contadorAtual + 1;

        String strSQL = "UPDATE IDENTIFICADORES SET Contador = " + novoContador + " WHERE Nome = 'PEDIDO'";
        database.execSQL(strSQL);
        //Log.w("CardapioCliente",strSQL);

        db.execSQL("INSERT INTO PEDIDO (ID, ClienteID, PrecoTotal, Status) VALUES ("+novoContador+","+Integer.parseInt(usuario)+","+Total+",'Enviado');");
        //Log.w("CardapioCliente","INSERT INTO PEDIDO (ID, ClienteID, PrecoTotal, Status) VALUES ("+novoContador+","+Integer.parseInt(usuario)+","+Total+",'Enviado');");
        //cursor.close();
        int pedidoID = novoContador;

        cursor = db.rawQuery("SELECT Contador FROM IDENTIFICADORES WHERE NOME = 'PEDIDOPRODUTO'",null);

        cursor.moveToFirst();
        contadorAtual = cursor.getInt(cursor.getColumnIndex("Contador"));
        cursor.close();

        novoContador = contadorAtual + 1;

        strSQL = "UPDATE IDENTIFICADORES SET Contador = " + novoContador + " WHERE Nome = 'PEDIDOPRODUTO'";
        database.execSQL(strSQL);
        //Log.w("CardapioCliente",strSQL);

        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        int cursorCount = cursor.getCount();
        String nomeProduto;
        String opcoes;
        int quantidade;
        int idNoCarrinho;

        if (cursor.moveToFirst()){
            do{
                nomeProduto = cursor.getString(cursor.getColumnIndex("Produto"));
                opcoes = cursor.getString(cursor.getColumnIndex("Opcoes"));
                quantidade = cursor.getInt(cursor.getColumnIndex("QuantidadeProduto"));
                idNoCarrinho = cursor.getInt(cursor.getColumnIndex("ID"));
                db.execSQL("INSERT INTO PEDIDOPRODUTO (ID ,PedidoID , Produto, QuantidadeProduto , PrecoTotal, Opcoes) VALUES ("+novoContador+","+pedidoID+",'"+nomeProduto+"',"+quantidade+","+Total+",'"+opcoes+"');");
                //Log.w("CardapioCliente","INSERT INTO PEDIDOPRODUTO (ID ,PedidoID , Produto, QuantidadeProduto , PrecoTotal, Opcoes) VALUES ("+novoContador+","+pedidoID+",'"+nomeProduto+"',"+quantidade+","+Total+",'"+opcoes+"');");
            }while(cursor.moveToNext());
        }
        cursor.close();

        double novoCredito = creditoUsuario - Total;

        strSQL = "UPDATE CREDITOS SET Credito = "+ novoCredito +"  WHERE Matricula = "+ Integer.parseInt(usuario);

        database.execSQL(strSQL);

        db.delete("CARRINHO", null, null);

        finish();

    }








}
