package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Tempo;

public class ConfiguracaoTempoActivity extends AppCompatActivity {

    private Tempo tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_tempo);

        if (getIntent().hasExtra("tempo")) {
            tempo = (Tempo) getIntent().getSerializableExtra("tempo");
        }
    }

    public void onClickSalvar(View view) {
        EditText editText = (EditText) findViewById(R.id.tempoInicial);
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        try {
            d = df.parse(editText.getText().toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        tempo.setTempoInicial(d.getTime());
        Intent intent = new Intent();
        intent.putExtra("tempo", (Serializable) tempo);
        setResult(RESULT_OK, intent);

        finish();
    }
}
