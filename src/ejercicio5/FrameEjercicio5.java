package ejercicio5;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class FrameEjercicio5 extends JFrame implements ActionListener, ItemListener {

    public void llenarArray(int[] array){
        for(int i = 0 ; i < array.length; i++){ // Lleno el array de números con números aleatorios
            array[i] = (int)(Math.random()*200+0);

            for(int j = 0 ; j< array.length; j++){
                if(i != j && array[i] == array[j]){ // Si hay algún número repetido en el array, se sustituirá por otro número aleatorio diferente
                    array[j] = (int)(Math.random()*200+0);
                }
            }
        }
    }

    JButton btnJugar;

    int[] numeros = new int[49];

    int numsEscogidos; // Creo una variable para controlar que se han escogido 6 números

    public FrameEjercicio5(){
        super("Ejercicio 5 Boletín Tema 9");
        setLayout(new FlowLayout());

        llenarArray(numeros);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

    }
}