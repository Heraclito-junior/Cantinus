package com.example.gilbertosoares.cantinus3;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by GilbertoSoares on 06/11/17.
 */

public class CreditoActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;

    private TextView mTextMatricula;

    private TextView mTextCredito;

    // Banco de Dados
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credito);

        mTextMatricula = (TextView) findViewById(R.id.fieldNome);
        mTextCredito = (TextView) findViewById(R.id.fieldPreco);

        CantinusSQLOpenHelper helper = new CantinusSQLOpenHelper(this);
        database = helper.getReadableDatabase();

        Button buttonCredito = (Button) findViewById(R.id.buttonCredito);
        buttonCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!addCredito(mTextMatricula.getText().toString(), Double.parseDouble(mTextCredito.getText().toString()))){

                    mTextMatricula.setError("Matrícula não encontrada no sistema");
                    mTextMatricula.setFocusableInTouchMode(true);
                    mTextMatricula.requestFocus();
                } else {
                    finish();
                }
            }
        });

    }

    public boolean addCredito(String matricula, double credito) {

        String[] columns = {
                "Credito"
        };
        SQLiteDatabase db = database;

        String selection = "Matricula = ?";

        String[] selectionArgs = {matricula};

        Cursor cursor = db.query("Creditos", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        double creditoAtual;

        int cursorCount = cursor.getCount();
        if (cursorCount == 0) { // matrícula inexistente
            return false;
        }

        cursor.moveToFirst();
        String retorno = cursor.getString(cursor.getColumnIndex("Credito"));
        cursor.close();
        creditoAtual = Double.parseDouble(retorno);

        double novoCredito = creditoAtual + credito;

        String strSQL = "UPDATE CREDITOS SET Credito = " + novoCredito + " WHERE Matricula = '"+ matricula + "'";

        database.execSQL(strSQL);

        return true;
    }
}
