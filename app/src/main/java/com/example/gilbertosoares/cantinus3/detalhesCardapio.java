package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class detalhesCardapio extends AppCompatActivity {
    Double valorOriginal = 0.0;
    Double valorSabor;
    private static final String TAG = "CardapioCliente";

    private SQLiteDatabase database;

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

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private CheckBox checkBox12;
    private CheckBox checkBox13;

    private TextView textTitulo;
    private TextView textDescricao;
    private TextView textValor;

    private int quantidadeOpcoes;
    private int limite = 1;

    private ArrayList<CheckBox> listaBoxes;

    ArrayList<String> opcoesEscolhidas;
    SelecaoMenu selecao;
    String conversorString;
    int Quantidade=1;
    Double TotalMostrado=0.0;
    String nomeProduto="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cardapio);
        TableLayout listaOpcoes = (TableLayout) findViewById(R.id.tabelaOpcao);
        final TextView totalParcial = (TextView) findViewById(R.id.valorTotal);

        textTitulo = (TextView) findViewById(R.id.textTitulo);
        textDescricao = (TextView) findViewById(R.id.textDescricao);
        textValor = (TextView) findViewById(R.id.textValor);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("ItemEscolhido", 0);

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        listaBoxes = new ArrayList<CheckBox>();

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        listaBoxes.add(checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        listaBoxes.add(checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        listaBoxes.add(checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        listaBoxes.add(checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        listaBoxes.add(checkBox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        listaBoxes.add(checkBox6);
        checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
        listaBoxes.add(checkBox7);
        checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
        listaBoxes.add(checkBox8);
        checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
        listaBoxes.add(checkBox9);
        checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
        listaBoxes.add(checkBox10);
        checkBox11 = (CheckBox) findViewById(R.id.checkBox11);
        listaBoxes.add(checkBox11);
        checkBox12 = (CheckBox) findViewById(R.id.checkBox12);
        listaBoxes.add(checkBox12);
        checkBox13 = (CheckBox) findViewById(R.id.checkBox13);
        listaBoxes.add(checkBox13);




        povoarOpcoes(intValue);

        quantidadeOpcoes = 0;

        opcoesEscolhidas=new ArrayList<String>();
        selecao=new SelecaoMenu();
        conversorString=new String();

        if(textTitulo.getText().equals("Tapioca")){
            limite = 2;
        }

        atualizarTotal();

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox1.getText().toString();

                if (textTitulo.getText().equals("Tapioca")) {
                    if (isChecked) {
                        opcoesEscolhidas.add(conversorString);
                        for (CheckBox box : listaBoxes) {
                            box.setChecked(false);
                            box.setEnabled(false);


                        }
                        listaBoxes.get(0).setChecked(true);
                        listaBoxes.get(0).setEnabled(true);

                    } else {
                        for(int i=0;i<opcoesEscolhidas.size();i++){
                            if(opcoesEscolhidas.get(i).equals(conversorString)){
                                opcoesEscolhidas.remove(i);
                                Log.w(TAG,"removeu");

                            }
                        }
                        Log.w(TAG,"uncheck");

                        for (CheckBox box : listaBoxes) {
                            box.setEnabled(true);
                        }
                    }
                } else {
                    if(isChecked){
                        opcoesEscolhidas.add(conversorString);

                        quantidadeOpcoes++;
                        if(quantidadeOpcoes == limite){
                            trancarBoxes();
                        }
                    }else{
                        for(int i=0;i<opcoesEscolhidas.size();i++){
                            if(opcoesEscolhidas.get(i).equals(conversorString)){
                                opcoesEscolhidas.remove(i);
                                Log.w(TAG,"removeu");

                            }
                        }
                        if(quantidadeOpcoes == limite){
                            abrirBoxes();
                        }
                        quantidadeOpcoes--;
                    }
                }
                atualizarTotal();
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox2.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);
                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                        Log.w(TAG,"check");

                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                        Log.w(TAG,"uncheck");

                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox3.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);
                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox4.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox5.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox6.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox7.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox8.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox9.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox10.getText().toString();


                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox11.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox12.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        checkBox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                conversorString=checkBox12.getText().toString();

                if(isChecked){
                    opcoesEscolhidas.add(conversorString);

                    quantidadeOpcoes++;
                    if(quantidadeOpcoes == limite){
                        trancarBoxes();
                    }
                }else{
                    for(int i=0;i<opcoesEscolhidas.size();i++){
                        if(opcoesEscolhidas.get(i).equals(conversorString)){
                            opcoesEscolhidas.remove(i);
                            Log.w(TAG,"removeu");

                        }
                    }
                    if(quantidadeOpcoes == limite){
                        abrirBoxes();
                    }
                    quantidadeOpcoes--;
                }
                atualizarTotal();
            }
        });

        Button btnAcao = (Button) findViewById(R.id.confimarProduto);

        btnAcao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finalizar();
            }
        });

        ImageButton plusBtn =(ImageButton) findViewById(R.id.plus);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                add();
            }
        });

        ImageButton minusBtn =(ImageButton) findViewById(R.id.minus);

        minusBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                remove();
            }
        });
    }

    public void povoarOpcoes(int produto){
        String[] columns = {
                "Nome"
        };

        SQLiteDatabase db = database;
        Cursor cursor;


        cursor=db.rawQuery("SELECT Nome, Descricao FROM PRODUTOS WHERE ID = " + produto, null);
        cursor.moveToFirst();
        nomeProduto=cursor.getString(cursor.getColumnIndex("Nome"));
        textTitulo.setText(cursor.getString(cursor.getColumnIndex("Nome")));
        textDescricao.setText(cursor.getString(cursor.getColumnIndex("Descricao")));
        cursor.close();

        cursor=db.rawQuery("SELECT Preco FROM PRODUTOS WHERE ID = " + produto, null);
        cursor.moveToFirst();
        valorOriginal = cursor.getDouble(cursor.getColumnIndex("Preco"));
        valorSabor = valorOriginal;
        cursor.close();

        cursor=db.rawQuery("SELECT * FROM OPCOES WHERE IDproduto = " + produto, null);

        int cursorCount = cursor.getCount();
        String sabor;
        int auxID = 0;

        if (cursor.moveToFirst()){
            do{
                sabor = cursor.getString(cursor.getColumnIndex("Nome"));
                listaBoxes.get(auxID).setVisibility(View.VISIBLE);
                listaBoxes.get(auxID).setText(sabor);
                auxID++;
            }while(cursor.moveToNext());
        }
        cursor.close();

    }

    public void trancarBoxes(){
        for(CheckBox box : listaBoxes){
            if(!box.isChecked()) {
                box.setEnabled(false);
            }
        }

    }

    public void abrirBoxes(){
        for(CheckBox box : listaBoxes){
            if(!box.isEnabled()) {
                box.setEnabled(true);
            }
        }

    }

    public void atualizarTotal(){
        if (textTitulo.getText().equals("Tapioca")){
            if(listaBoxes.get(0).isChecked() || quantidadeOpcoes == 0){
                valorSabor = valorOriginal;
            } else if(listaBoxes.get(9).isChecked()){
                valorSabor = valorOriginal + 3.50;
            } else {
                valorSabor = valorOriginal + 2.50;
            }
        }
        Double ValorTemp=valorSabor*Quantidade;
        textValor.setText("Valor: R$ " + ValorTemp);
    }

    public void finalizar(){

        SQLiteDatabase db = database;
        Cursor cursor;
        int idsAtual=0;
        int ultimoID=0;

        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        int cursorCount = cursor.getCount();

        if(cursorCount==13){

            Context context = getApplicationContext();
            CharSequence text = "Só pode ter até 13 produtos no carrinho";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }


        if (cursor.moveToFirst()){
            do{
                ultimoID=cursor.getInt(cursor.getColumnIndex("ID"));

            }while(cursor.moveToNext());
        }
        cursor.close();

        Log.w(TAG,"id Final"+ultimoID);





        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        idsAtual = cursor.getCount();

        Log.w(TAG,"cursores "+idsAtual);

        int novoID=idsAtual+1;

        String parser="";


        for(int i=0;i<opcoesEscolhidas.size();i++){
            if(i + 1 < opcoesEscolhidas.size()) {
                parser = parser + opcoesEscolhidas.get(i) + ", ";
            } else {
                parser = parser + opcoesEscolhidas.get(i);
            }
        }




        TotalMostrado=valorSabor*Quantidade;

        db.execSQL("INSERT INTO CARRINHO (ID, Produto, QuantidadeProduto,PrecoTotal,Opcoes) VALUES ("+novoID+",'"+nomeProduto+"',"+Quantidade+","+TotalMostrado+",'"+parser+"');");
        //db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (1,'Simples', 1);");
        cursor.close();


        cursor=db.rawQuery("SELECT * FROM CARRINHO", null);

        cursorCount = cursor.getCount();
        String sabor;

        if (cursor.moveToFirst()){
            do{
                sabor = cursor.getString(cursor.getColumnIndex("PrecoTotal"));
                Log.w(TAG,"teste "+sabor);
                //listaBoxes.get(auxID).setVisibility(View.VISIBLE);
                //listaBoxes.get(auxID).setText(sabor);
            }while(cursor.moveToNext());
        }
        cursor.close();


        finish();


        // db.execSQL("CREATE TABLE CARRINHO(ID  INT NOT NULL, Produto TEXT    NOT NULL, QuantidadeProduto  INT NOT NULL, PrecoTotal DOUBLE NOT NULL, Opcoes TEXT    NOT NULL );");
        /**

         cursor.moveToFirst();
         textTitulo.setText(cursor.getString(cursor.getColumnIndex("Nome")));
         textDescricao.setText(cursor.getString(cursor.getColumnIndex("Descricao")));
         cursor.close();

         */



        for(int i=0;i<opcoesEscolhidas.size();i++){
            Log.w(TAG,opcoesEscolhidas.get(i));

        }
        Log.w(TAG,"botao");

    }

    public void add(){
        TextView quantiCarrinho = (TextView) findViewById(R.id.QuantidadeDetalhes);
        Quantidade+=1;

        TotalMostrado=valorSabor*Quantidade;

        textValor.setText("Valor: R$ " + TotalMostrado);

        quantiCarrinho.setText(String.valueOf(Quantidade));


    }

    public void remove(){
        if(Quantidade<=1){

            Context context = getApplicationContext();
            CharSequence text = "Quantidade deve ser pelo menos 1";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }
        TextView quantiCarrinho = (TextView) findViewById(R.id.QuantidadeDetalhes);
        Quantidade-=1;

        TotalMostrado=valorSabor*Quantidade;

        textValor.setText("Valor: R$ " + TotalMostrado);




        quantiCarrinho.setText(String.valueOf(Quantidade));


    }


}
