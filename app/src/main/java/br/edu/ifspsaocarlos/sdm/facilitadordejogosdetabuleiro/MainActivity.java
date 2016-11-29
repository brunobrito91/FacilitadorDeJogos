package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

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

        expListView = (ExpandableListView) findViewById(R.id.listaOpcoes);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, opcoesCollection);
        expListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

    }

    public void onClickItem(View view) {
        final String selected = ((TextView) view).getText().toString();
        Intent intent = null;
        if (selected.equals("Cronômetro") || selected.equals("Ampulheta")) {
            intent = new Intent(getBaseContext(), TempoActivity.class);
        } else if (selected.equals("Dado") || selected.equals("Cara ou Coroa")) {
            intent = new Intent(getBaseContext(), SorteioActivity.class);
        }
        intent.putExtra("tipo", selected);
        startActivity(intent);
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Tempo");
        groupList.add("Sorteio");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] itensTempo = {"Cronômetro", "Ampulheta"};
        String[] itensSorteador = {"Dado", "Cara ou Coroa"};

        opcoesCollection = new LinkedHashMap<String, List<String>>();

        for (String opcao : groupList) {
            if (opcao.equals("Tempo")) {
                loadChild(itensTempo);
            } else if (opcao.equals("Sorteio"))
                loadChild(itensSorteador);
            opcoesCollection.put(opcao, childList);
        }
    }

    private void loadChild(String[] opcoesItens) {
        childList = new ArrayList<String>();
        for (String item : opcoesItens)
            childList.add(item);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
}