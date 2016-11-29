package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Sorteio;

public class Dado implements Sorteio {

    List<Number> faces = new ArrayList<>();

    public Dado() {
        faces.add(R.drawable.dado_face_1);
        faces.add(R.drawable.dado_face_2);
        faces.add(R.drawable.dado_face_3);
        faces.add(R.drawable.dado_face_4);
        faces.add(R.drawable.dado_face_5);
        faces.add(R.drawable.dado_face_6);
    }

    @Override
    public int getImagemResource() {
        Collections.shuffle(faces);
        return (int) faces.get(0);
    }
}
