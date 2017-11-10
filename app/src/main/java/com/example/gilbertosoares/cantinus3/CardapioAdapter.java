package com.example.gilbertosoares.cantinus3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Heraclito on 10/10/2017.
 */

public class CardapioAdapter extends ArrayAdapter<ItemCardapio>{

    private final Context context;
    ArrayList<ItemCardapio> cardapioM = new ArrayList<ItemCardapio>();

    public CardapioAdapter(Context context, ArrayList<ItemCardapio> cardap) {
        super(context, R.layout.cardapiolayout, cardap);
        this.context = context;
        this.cardapioM = cardap;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.cardapiolayout, parent, false);

        TextView textNome = (TextView) rowView.findViewById(R.id.nome);
        TextView textDescricao = (TextView) rowView.findViewById(R.id.descricao);
        TextView textPreco = (TextView) rowView.findViewById(R.id.preco);

        ItemCardapio cardapio = cardapioM.get(position);

        textNome.setText(cardapio.getNome());
        textDescricao.setText(cardapio.getDescricao());

        String prec=String.valueOf(cardapio.getPreco());

        textPreco.setText(prec);

        return rowView;
    }

}




