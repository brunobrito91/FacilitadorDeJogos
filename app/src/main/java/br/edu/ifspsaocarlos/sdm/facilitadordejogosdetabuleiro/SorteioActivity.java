package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.CaraCoroa;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Dado;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Sorteio;

public class SorteioActivity extends AppCompatActivity {

    private Sorteio sorteio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio);
        //verifica se quem chamou essa activity passou o parametro tipo
        if (getIntent().hasExtra(getString(R.string.tipo))) {
            //recupera o valor passado no parametro tipo
            String tipo = getIntent().getStringExtra(getString(R.string.tipo));
            //verifica qual item foi selecionado na activity que chamou essa activity e cria um Sorteio
            if (tipo.equals(getString(R.string.dado))) {
                sorteio = new Dado();
            } else if (tipo.equals(getString(R.string.cara_coroa))) {
                sorteio = new CaraCoroa();
            }
        }
    }

    public void onClickSortear(View view) {
        //recupera o ImageView
        ImageView imgView = (ImageView) findViewById(R.id.imgSorteio);
        //seta a imagem com o retorno do sorteio realizado
        imgView.setImageResource(sorteio.getImagemResource());
        //exibe uma mensagem quando o botão para sortear é pressionado
        Toast.makeText(this, getString(R.string.sorteio_realizado), Toast.LENGTH_SHORT).show();
    }
}
