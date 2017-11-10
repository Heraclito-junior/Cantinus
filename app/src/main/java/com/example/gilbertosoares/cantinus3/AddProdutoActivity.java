package com.example.gilbertosoares.cantinus3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddProdutoActivity extends AppCompatActivity {

    private TextView mTextNome;
    private TextView mTextPreco;
    private TextView mTextDescricao;

    // Banco de Dados
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        mTextNome = (TextView) findViewById(R.id.fieldNome);
        mTextPreco = (TextView) findViewById(R.id.fieldPreco);
        mTextDescricao = (TextView) findViewById(R.id.fieldDescricao);

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        Button buttonProduto = (Button) findViewById(R.id.buttonProduto);
        buttonProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = addProduto(mTextNome.getText().toString(), Double.parseDouble(mTextPreco.getText().toString()), mTextDescricao.getText().toString());
                if(result == 0){

                    mTextNome.setError("Matrícula não encontrada no sistema");
                    mTextNome.setFocusableInTouchMode(true);
                    mTextNome.requestFocus();
                } else {
                    finish();
                }
            }
        });
    }

    public int addProduto(String nome, double preco, String descricao) {

        String[] columns = {
                "Contador"
        };
        SQLiteDatabase db = database;

        String selection = "Nome = ?";

        String[] selectionArgs = {"PRODUTOS"};

        Cursor cursor = db.query("Identificadores", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        double contadorAtual;

        int cursorCount = cursor.getCount();
        if (cursorCount == 0) { // matrícula inexistente
            return 0;
        }

        cursor.moveToFirst();
        String retorno = cursor.getString(cursor.getColumnIndex("Contador"));
        cursor.close();
        contadorAtual = Double.parseDouble(retorno);

        double novoContador = contadorAtual + 1;

        String strSQL = "UPDATE IDENTIFICADORES SET Contador = " + novoContador + " WHERE Nome = 'PRODUTOS'";
        database.execSQL(strSQL);

        db.execSQL("INSERT INTO PRODUTOS (ID,Nome, Preco, Descricao) VALUES (" + novoContador + ",'" + nome + "', " + preco + ", '" + descricao +"');");
        database.execSQL(strSQL);

        return 1;
    }
}
