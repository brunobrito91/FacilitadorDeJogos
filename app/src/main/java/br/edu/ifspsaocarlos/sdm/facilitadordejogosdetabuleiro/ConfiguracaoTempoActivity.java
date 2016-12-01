package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfiguracaoTempoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_tempo);
    }

    public void onClickSalvar(View view) {
        //recupera o EditText
        EditText editText = (EditText) findViewById(R.id.tempoInicial);
        //converte o texto do formato [mm:ss] para data
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        try {
            d = df.parse(editText.getText().toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        //cria um intent
        Intent intent = new Intent();
        //atribui ao parametro tempo o valor do tempo em long
        intent.putExtra("tempo", d.getTime() - 10800000);
        //seta o result com o intent e diz que o resultado está ok
        setResult(RESULT_OK, intent);
        //finaliza a Activity
        finish();
    }
}
