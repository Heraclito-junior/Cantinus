package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gilbert0 on 17/10/2017.
 */

public class CantinusSQLOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bd_cantinus";
    private static final int DATABASE_VERSION = 9;
    CantinusSQLOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS CONTAS");
        db.execSQL("CREATE TABLE CONTAS(ID  INT NOT NULL, Senha TEXT    NOT NULL, Classe    TEXT    NOT NULL);");
        db.execSQL("INSERT INTO CONTAS (ID, Senha, Classe) VALUES (201402, '123456', 'Gerente');");
        db.execSQL("INSERT INTO CONTAS (ID, Senha, Classe) VALUES (201488, '123456', 'Cliente');");
        db.execSQL("INSERT INTO CONTAS (ID, Senha, Classe) VALUES (201494, '123456', 'Cliente');");
        db.execSQL("INSERT INTO CONTAS (ID, Senha, Classe) VALUES (201482, '123456', 'Cliente');");
        db.execSQL("INSERT INTO CONTAS (ID, Senha, Classe) VALUES (201430, '123456', 'Cliente');");
        onUpgrade(db, 1, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch(oldVersion){
            case 1:
                db.execSQL("DROP TABLE IF EXISTS CREDITOS");
                db.execSQL("CREATE TABLE CREDITOS(Matricula  INT NOT NULL, Credito DOUBLE    NOT NULL);");
                db.execSQL("INSERT INTO CREDITOS (Matricula, Credito) VALUES (201488, 30.00);");
                db.execSQL("INSERT INTO CREDITOS (Matricula, Credito) VALUES (201494, 23.00);");
                db.execSQL("INSERT INTO CREDITOS (Matricula, Credito) VALUES (201482, 16.00);");
                db.execSQL("INSERT INTO CREDITOS (Matricula, Credito) VALUES (201430, 9.00);");

            case 2:
                db.execSQL("DROP TABLE IF EXISTS PRODUTOS");
                db.execSQL("CREATE TABLE PRODUTOS(ID  INT NOT NULL, Nome TEXT    NOT NULL, Preco    DOUBLE  NOT NULL, Descricao TEXT    NOT NULL);");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (1,'Tapioca', 3.00, 'Tapioca simples ou recheada');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (2,'Torrada', 1.50, 'Pão de forma e queijo');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (3,'Milkshake', 4.75, 'Milkshake com leite');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (4,'Pizza Brotinho', 5.00, 'E ai Broto');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (5,'Cachorro Quente', 3.50, 'Pão, carne e salsicha');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (6,'Hamburger', 3.15, 'Pão, hambúrguer, queijo e salada');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (7,'Misto', 1.75, 'Pão, queijo e presunto');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (8,'Marmita', 6.99, 'Surpresa');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (9,'Suco', 3.00, 'Suco de fruta');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (10,'Suco Especial', 4.00, 'Morango e combinações');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (11,'Vitamina', 3.50, 'Vitamina de fruta no leite');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (12,'Café', 0.75, 'Café puro');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (13,'Café com Leite', 1.50, 'Café com leite');");
                db.execSQL("INSERT INTO PRODUTOS (ID, Nome, Preco, Descricao) VALUES (14,'Café Especial', 2.75, 'Expresso, chocolate quente, cappucino');");

            case 3:
                db.execSQL("DROP TABLE IF EXISTS IDENTIFICADORES");
                db.execSQL("CREATE TABLE IDENTIFICADORES(Contador  INT NOT NULL, Nome TEXT    NOT NULL);");
                db.execSQL("INSERT INTO IDENTIFICADORES (Contador, Nome) VALUES (14,'PRODUTOS');");

            case 4:
                db.execSQL("DROP TABLE IF EXISTS OPCOES");
                db.execSQL("CREATE TABLE OPCOES(ID  INT NOT NULL, Nome TEXT    NOT NULL, IDproduto  INT Null NULL);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (1,'Simples', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (2,'Queijo de coalho', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (3,'Mussarela', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (4,'Catupiry', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (5,'Frango', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (6,'Presunto', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (7,'Ovo', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (8,'Calabresa', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (9,'Bacon', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (10,'Carne de Sol', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (11,'Mortadela defumada', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (12,'Salsicha', 1);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (13,'Cheddar', 1);");

                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (14,'Cajá', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (15,'Goiaba', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (16,'Acerola', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (17,'Uva', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (18,'Mangaba', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (19,'Laranja', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (20,'Maracujá', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (21,'Cupuaçú', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (22,'Graviola', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (23,'Ameixa', 9);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (24,'Limão', 9);");

                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (25,'Chocolate', 3);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (26,'Creme energético', 3);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (27,'Morango', 3);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (28,'Creme', 3);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (29,'Flocos', 3);");

                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (30,'Morango', 10);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (31,'Acerola com laranja', 10);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (32,'Mamão com laranja', 10);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (33,'Abacaxi com hortelã', 10);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (34,'Limão com laranja', 10);");

                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (35,'Abacate', 11);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (36,'Banana', 11);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (37,'Mamão', 11);");

                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (38,'Frango', 4);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (39,'Calabresa', 4);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (40,'Mussarela', 4);");

                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (41,'Café expresso', 14);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (42,'Chocolate quente', 14);");
                db.execSQL("INSERT INTO OPCOES (ID, Nome, IDproduto) VALUES (43,'Capuccino', 14);");
            case 5:
                db.execSQL("DROP TABLE IF EXISTS CARRINHO");
                db.execSQL("CREATE TABLE CARRINHO(ID  INT NOT NULL, Produto TEXT    NOT NULL, QuantidadeProduto  INT NOT NULL, PrecoTotal DOUBLE NOT NULL, Opcoes TEXT    NOT NULL );");
            case 6:
                db.execSQL("DROP TABLE IF EXISTS PEDIDO");
                db.execSQL("CREATE TABLE PEDIDO(ID  INT NOT NULL, ClienteID INT    NOT NULL, PrecoTotal DOUBLE NOT NULL, Status TEXT    NOT NULL );");

                db.execSQL("DROP TABLE IF EXISTS PEDIDOPRODUTO");
                db.execSQL("CREATE TABLE PEDIDOPRODUTO(ID  INT NOT NULL,PedidoID INT    NOT NULL, Produto TEXT    NOT NULL, QuantidadeProduto  INT NOT NULL, PrecoTotal DOUBLE NOT NULL, Opcoes TEXT    NOT NULL );");

            case 7:
                db.execSQL("INSERT INTO PEDIDO (ID, ClienteID, PrecoTotal, Status) VALUES (1,201488, 4.75, 'Enviado');");
                db.execSQL("INSERT INTO PEDIDO (ID, ClienteID, PrecoTotal, Status) VALUES (2,201488, 8.15, 'Confirmado');");


                db.execSQL("INSERT INTO PEDIDOPRODUTO (ID, PedidoID, Produto, QuantidadeProduto, PrecoTotal, Opcoes) VALUES (1,1,'Misto',1, 4.75,'');");
                db.execSQL("INSERT INTO PEDIDOPRODUTO (ID, PedidoID, Produto, QuantidadeProduto, PrecoTotal, Opcoes) VALUES (2,1,'Suco',1, 3.00,'Laranja');");
                db.execSQL("INSERT INTO PEDIDOPRODUTO (ID, PedidoID, Produto, QuantidadeProduto, PrecoTotal, Opcoes) VALUES (3,2,'Hamburger',1, 3.15,'');");
                db.execSQL("INSERT INTO PEDIDOPRODUTO (ID, PedidoID, Produto, QuantidadeProduto, PrecoTotal, Opcoes) VALUES (4,2,'Pizza Brotinho',1, 3.15,'Mussarela');");
            case 8:
                db.execSQL("DROP TABLE IF EXISTS IDENTIFICADORES");
                db.execSQL("CREATE TABLE IDENTIFICADORES(Contador  INT NOT NULL, Nome TEXT    NOT NULL);");
                db.execSQL("INSERT INTO IDENTIFICADORES (Contador, Nome) VALUES (14,'PRODUTOS');");
                db.execSQL("INSERT INTO IDENTIFICADORES (Contador, Nome) VALUES (2,'PEDIDO');");
                db.execSQL("INSERT INTO IDENTIFICADORES (Contador, Nome) VALUES (4,'PEDIDOPRODUTO');");
        }
    }
}