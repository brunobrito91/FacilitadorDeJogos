package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Ampulheta;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Cronometro;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Tempo;

public class TempoActivity extends AppCompatActivity {

    private static final int CONFIGURACAO = 0;
    private Chronometer chronometer;
    private Tempo tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempo);

        //verifica se quem chamou essa activity passou o parametro tipo
        if (getIntent().hasExtra(getString(R.string.tipo))) {
            //recupera o Chronometer
            chronometer = (Chronometer) findViewById(R.id.timer);
            //recupera o valor passado no parametro tipo
            String tipo = getIntent().getStringExtra(getString(R.string.tipo));
            //verifica qual item foi selecionado na activity que chamou essa activity e cria um Tempo
            if (tipo.equals(getString(R.string.cronometro))) {
                tempo = new Cronometro(chronometer);
            }
            if (tipo.equals(getString(R.string.ampulheta))) {
                tempo = new Ampulheta(chronometer);
            }
        }
    }

    public void onClickToggleStartPauseContinue(View view) {
        //recupera o botão que foi pressionado
        Button btnToggleStartPause = (Button) view;
        //recupera o texto contido no botão no momento em que foi pressionado
        String txtBtn = btnToggleStartPause.getText().toString();
        /*inicia a contagem se foi pressionado o botão com o texto start ou continue
          pausa a contagem se foi pressionado o botão com o texto pause*/
        if (tempo != null) {
            if (txtBtn.equals(getString(R.string.txt_btn_start)) || txtBtn.equals(getString(R.string.txt_btn_continuar))) {
                tempo.start();
                btnToggleStartPause.setText(getString(R.string.txt_btn_pause));
            } else if (txtBtn.equals(getString(R.string.txt_btn_pause))) {
                tempo.pause();
                btnToggleStartPause.setText(getString(R.string.txt_btn_continuar));
            }
        }
    }

    public void onClickStop(View view) {
        //recupera o botão toggleStartPause
        Button btnToggleStartPause = (Button) findViewById(R.id.btnToggleStartPause);
        //zera a contagem
        tempo.stop();
        btnToggleStartPause.setText(getString(R.string.txt_btn_start));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //infla o menu de configuração de tempo se o tempo for Ampulheta
        if (tempo instanceof Ampulheta) {
            getMenuInflater().inflate(R.menu.menu_configuracao_tempo, menu);
            return true;
        }
        return false;
    }

    public void onClickConfiguracao(MenuItem menuItem) {
        //inicia a Activity ConfiguracaoTempoActivity quando a opção Configuração do menu é selecionada
        Intent intent = new Intent(getBaseContext(), ConfiguracaoTempoActivity.class);
        startActivityForResult(intent, CONFIGURACAO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*verifica se o retorno da Activity ConfiguracaoTempoActivity retornou com sucesso
          recupera o valor retornado através do parametro tempo e inicializa as configurações do tempo com o valor configurado
          na tela ConfiguracaoTempoActivity*/
        if (requestCode == CONFIGURACAO) {
            if (resultCode == RESULT_OK) {
                long t = data.getLongExtra("tempo", 0);
                tempo.inicializa(t);
            }
        }
    }
}
