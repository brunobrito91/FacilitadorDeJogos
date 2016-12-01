package br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.R;
import br.edu.ifspsaocarlos.sdm.facilitadordejogosdetabuleiro.model.Sorteio;

public class Dado implements Sorteio {

    List<Number> faces = new ArrayList<>();

    public Dado() {
        //adiciona a lista de imagens as opções[face 1, face 2, face 3, face 4, face 5, face 6]
        faces.add(R.drawable.dado_face_1);
        faces.add(R.drawable.dado_face_2);
        faces.add(R.drawable.dado_face_3);
        faces.add(R.drawable.dado_face_4);
        faces.add(R.drawable.dado_face_5);
        faces.add(R.drawable.dado_face_6);
    }

    @Override
    public int getImagemResource() {
        //reordena a lista de imagens
        Collections.shuffle(faces);
        //recupera uma imagem aleatoria
        return (int) faces.get(0);
    }
}
