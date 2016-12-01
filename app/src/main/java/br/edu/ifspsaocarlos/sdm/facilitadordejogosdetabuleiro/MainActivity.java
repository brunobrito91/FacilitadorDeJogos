package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.adapter.ExpandableListAdapter;

public class MainActivity extends AppCompatActivity {

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> opcoesCollection;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createGroupList();

        createCollection();

        //recupera a ExpandableListView
        expListView = (ExpandableListView) findViewById(R.id.listaOpcoes);
        //cria o adapter pra ExpandableListView
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, opcoesCollection);
        //seta o adapter para o ExpandableListView
        expListView.setAdapter(expListAdapter);
    }

    public void onClickItem(View view) {
        //verifica o item que foi clicado
        final String selected = ((TextView) view).getText().toString();
        Intent intent = null;
        //se item for de [Tempo] inicia a Activity TempoActivity
        //se item for de [Sorteio] inicia a Activity SorteioActivity
        if (selected.equals(getString(R.string.cronometro)) || selected.equals(getString(R.string.ampulheta))) {
            intent = new Intent(getBaseContext(), TempoActivity.class);
        } else if (selected.equals(getString(R.string.dado)) || selected.equals(getString(R.string.cara_coroa))) {
            intent = new Intent(getBaseContext(), SorteioActivity.class);
        }
        //passa o tipo com o item selecionado para que na Activity que for chamada possa ser criado a classe correta
        intent.putExtra(getString(R.string.tipo), selected);
        //inicia Activity
        startActivity(intent);
    }

    private void createGroupList() {
        //adiciona opções na lista de opções
        groupList = new ArrayList<String>();
        groupList.add(getString(R.string.txt_tempo));
        groupList.add(getString(R.string.txt_sorteio));
    }

    private void createCollection() {
        //cria itens para opção [Tempo] e [Sorteio]
        String[] itensTempo = {getString(R.string.cronometro), getString(R.string.ampulheta)};
        String[] itensSorteador = {getString(R.string.dado), getString(R.string.cara_coroa)};

        opcoesCollection = new LinkedHashMap<String, List<String>>();
        /*cria a estrutura da lista expansivel
        Tempo
           ->Cronômetro
           ->Ampulheta
        Sorteio
           ->Dado
           ->Cara ou Coroa*/
        for (String opcao : groupList) {
            if (opcao.equals(getString(R.string.txt_tempo))) {
                loadChild(itensTempo);
            } else if (opcao.equals(getString(R.string.txt_sorteio)))
                loadChild(itensSorteador);
            opcoesCollection.put(opcao, childList);
        }
    }

    private void loadChild(String[] opcoesItens) {
        //adiciona itens na lista de itens
        childList = new ArrayList<String>();
        for (String item : opcoesItens)
            childList.add(item);
    }
}