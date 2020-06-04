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

    int numsEscogidos = 0; // Creo una variable para controlar que se han escogido 6 números
    ArrayList<JCheckBox> checkNumeros = new ArrayList<>(); // Creo una colección donde guardar los checkbox de los números

    public FrameEjercicio5(){
        super("Ejercicio 5 Boletín Tema 9");
        setLayout(new FlowLayout());

        llenarArray(numeros);

        for (int i = 0; i < numeros.length; i++) {
            JCheckBox chkNumero = new JCheckBox(String.valueOf(numeros[i])); // Creo un checkbox por cada número
            chkNumero.setSize(chkNumero.getPreferredSize());
            chkNumero.addActionListener(this);
            this.add(chkNumero);
            checkNumeros.add(chkNumero);
        }

        btnJugar = new JButton("Jugar");
        btnJugar.setSize(btnJugar.getPreferredSize());
        btnJugar.setEnabled(false);
        btnJugar.addActionListener(this);
        add(btnJugar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == JCheckBox.class){ // Acciones a realizar si se ha interactuado con un JCheckBox

            for (int i = 0; i < checkNumeros.size(); i++) {
                if(e.getSource() == checkNumeros.get(i) && checkNumeros.get(i).isSelected()){
                    numsEscogidos ++;
                }
                else if(e.getSource() == checkNumeros.get(i) && !checkNumeros.get(i).isSelected()){
                    numsEscogidos --;
                }
            }

            if(numsEscogidos == 6){
                btnJugar.setEnabled(true);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

    }
}