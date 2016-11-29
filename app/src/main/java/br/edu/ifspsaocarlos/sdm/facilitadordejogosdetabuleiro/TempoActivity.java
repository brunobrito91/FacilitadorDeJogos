package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Ampulheta;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Cronometro;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Tempo;

public class TempoActivity extends AppCompatActivity {

    private static final int CONFIGURACAO_AMPULHETA = 0;
    private Chronometer chronometer;
    private ContadorRegressivo contadorRegressivo;
    private Tempo tempo;
    private long milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo);

        chronometer = (Chronometer) findViewById(R.id.timer);

        if (getIntent().hasExtra("tipo")) {
            String tipo = getIntent().getStringExtra("tipo");
            if (tipo.equals("Cronômetro")) {
                tempo = new Cronometro();
            }
            if (tipo.equals("Ampulheta")) {
                tempo = new Ampulheta();
                contadorRegressivo = new ContadorRegressivo(this, chronometer, tempo.getTempoInicial(), 100);
                chronometer.setText(DateFormat.format("mm:ss", tempo.getTempoInicial()));
            }
        }

    }

    public void onClickToggleStartPauseContinue(View view) {
        Button btnToggleStartPause = (Button) view;
        String txtBtn = btnToggleStartPause.getText().toString();
        if (tempo != null) {
            if (tempo instanceof Cronometro)
                toggleStarPauseContinueCronometro(btnToggleStartPause, txtBtn);
            if (tempo instanceof Ampulheta)
                toggleStarPauseContinueAmpulheta(btnToggleStartPause, txtBtn);
        }
    }

    private void toggleStarPauseContinueAmpulheta(Button btnToggleStartPause, String txtBtn) {
        if (txtBtn.equals("Start") || txtBtn.equals("Continue")) {
            contadorRegressivo.start();
            btnToggleStartPause.setText("Pause");
        } else if (txtBtn.equals("Pause")) {
            contadorRegressivo.cancel();
            long t = 1;
            SimpleDateFormat sf = new SimpleDateFormat("mm:ss");
            try {
                t = sf.parse(chronometer.getText().toString()).getTime() - 10800000;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            contadorRegressivo = new ContadorRegressivo(this, chronometer, t, 1000);
            btnToggleStartPause.setText("Continue");
        }
    }

    private void toggleStarPauseContinueCronometro(Button btnToggleStartPause, String txtBtn) {
        if (txtBtn.equals("Start") || txtBtn.equals("Continue")) {
            chronometer.setBase(SystemClock.elapsedRealtime() - milliseconds);
            chronometer.start();
            btnToggleStartPause.setText("Pause");
        } else if (txtBtn.equals("Pause")) {
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            btnToggleStartPause.setText("Continue");
        }
    }

    public void onClickStop(View view) {
        if (tempo instanceof Cronometro)
            clickStopCronometro();
        else if (tempo instanceof Ampulheta)
            clickStopAmpulheta();
        Button btnToggleStartPause = (Button) findViewById(R.id.btnToggleStartPause);
        btnToggleStartPause.setText("Start");
    }

    private void clickStopAmpulheta() {
        contadorRegressivo.cancel();
        contadorRegressivo = new ContadorRegressivo(this, chronometer, tempo.getTempoInicial(), 1000);
        chronometer.setText(DateFormat.format("mm:ss", tempo.getTempoInicial()));
    }

    private void clickStopCronometro() {
        milliseconds = 0;
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (tempo instanceof Ampulheta) {
            getMenuInflater().inflate(R.menu.menu_configuracao_tempo, menu);
            return true;
        }
        return false;
    }

    public void onClickConfiguracao(MenuItem menuItem) {
        Intent intent = new Intent(getBaseContext(), ConfiguracaoTempoActivity.class);
        intent.putExtra("tempo", (Serializable) tempo);
        startActivityForResult(intent, CONFIGURACAO_AMPULHETA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONFIGURACAO_AMPULHETA) {
            if (resultCode == RESULT_OK) {
                tempo = (Tempo) data.getSerializableExtra("tempo");
                contadorRegressivo = new ContadorRegressivo(this, chronometer, tempo.getTempoInicial(), 100);
                chronometer.setText(DateFormat.format("mm:ss", tempo.getTempoInicial()));
            }
        }
    }
}
