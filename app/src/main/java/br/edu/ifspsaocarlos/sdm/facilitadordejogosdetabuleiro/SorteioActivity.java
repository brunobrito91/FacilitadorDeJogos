package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Sorteio;

public class SorteioActivity extends AppCompatActivity {

    Sorteio sorteio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteio);
        if (getIntent().hasExtra("tipo")) {
            String tipo = getIntent().getStringExtra("tipo");
            if (tipo.equals("Dado")) {
                sorteio = new Dado();
            }
            else if (tipo.equals("Cara ou Coroa")) {
                sorteio = new CaraCoroa();
            }
        }
    }

    public void onClickSortear(View view){
        ImageView imgView = (ImageView) findViewById(R.id.imgSorteio);
        imgView.setImageResource(sorteio.getImagemResource());
        Toast.makeText(this,"Sorteio realizado" , Toast.LENGTH_SHORT).show();
    }
}
