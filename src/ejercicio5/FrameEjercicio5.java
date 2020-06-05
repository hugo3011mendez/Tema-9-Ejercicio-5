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

    JMenuBar menu;
    JMenu mnuMenu;
    JMenuItem mnuGuardar, mnuRecords;


    int[] numeros = new int[49];

    int numsEscogidos = 0; // Creo una variable para controlar que se han escogido 6 números
    ArrayList<JCheckBox> checkNumeros = new ArrayList<>(); // Creo una colección donde guardar los checkbox de los números
    ArrayList<Integer> numsSeleccionados = new ArrayList<>(); // Creo otra colección que guardará los números seleccionados
    ArrayList<Integer> numsAcertados = new ArrayList<>(); // Creo una colección para guardar los números acertados en el archivo al mostrarse la nueva ventana

    ArrayList<JLabel> etiquetasNumeros = new ArrayList<>(); // Creo una colección para los números que se mostrarán en la lotería

    public FrameEjercicio5(){
        super("Ejercicio 5 Boletín Tema 9");
        setLayout(new FlowLayout());

        // Guardar
        mnuGuardar = new JMenuItem("Guardar última partida");
        mnuGuardar.setMnemonic('G');
        mnuGuardar.addActionListener(this);

        // Récords
        mnuRecords = new JMenuItem("Ver récords");
        mnuRecords.setMnemonic('R');
        mnuRecords.addActionListener(this);

        // Menú
        mnuMenu = new JMenu("Menú");
        mnuMenu.add(mnuGuardar);
        mnuMenu.add(mnuRecords);

        // Barra de menú
        menu = new JMenuBar();
        menu.add(mnuMenu);
        this.setJMenuBar(menu);

        llenarArray(numeros); // Lleno el array de números aleatorios entre 0 y 200

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

        for (int i = 0; i < 6; i++) {
            JLabel lblNum = new JLabel();
            add(lblNum);

            etiquetasNumeros.add(lblNum);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == JCheckBox.class){ // Acciones a realizar si se ha interactuado con un JCheckBox
            for (int i = 0; i < checkNumeros.size(); i++) { // Recorro la colección de CheckBox y actualizo la variable que recoge los que están seleccionados
                if(e.getSource() == checkNumeros.get(i) && checkNumeros.get(i).isSelected()){
                    numsEscogidos ++;
                    numsSeleccionados.add(Integer.parseInt(checkNumeros.get(i).getText())); // Añado el número del CheckBox seleccionado a la colección de números seleccionados
                }
                else if(e.getSource() == checkNumeros.get(i) && !checkNumeros.get(i).isSelected()){
                    numsEscogidos --;
                    numsSeleccionados.remove(Integer.parseInt(checkNumeros.get(i).getText())); // Elimino el número de la colección al desseleccionarse el checkbox
                }
            }

            if(numsEscogidos == 6){ // Si hay 6 números seleccionados activo el botón, en caso contrario lo desactivo
                btnJugar.setEnabled(true);
            }
            else{
                btnJugar.setEnabled(false);
            }
        }
        else if(e.getSource() == btnJugar){ // Acciones a realizar cuando se pulse el botón de jugar
            ArrayList<Integer> numsDeLaMaquina = new ArrayList<>(); // Creo una colección para guardar los números que elige aleatoriamente la máquina
            for (int i = 0; i < 6; i++) {
                numsDeLaMaquina.add((int)(Math.random()*200+0));
            }

            boolean acertado = false; // Creo una variable para controlar si el número ha sido acertado
            for (int i = 0; i < numsDeLaMaquina.size(); i++) {
                for (int j = 0; j < numsSeleccionados.size(); j++) {
                    acertado = false;
                    if(numsDeLaMaquina.get(i) == numsSeleccionados.get(j)){
                        acertado = true;
                    }
                }

                // Introduzco el número en la etiqueta
                etiquetasNumeros.get(i).setText(String.valueOf(numsDeLaMaquina.get(i)));
                etiquetasNumeros.get(i).setSize(etiquetasNumeros.get(i).getPreferredSize());
                if(acertado){
                    etiquetasNumeros.get(i).setForeground(Color.GREEN);
                    numsAcertados.add(numsDeLaMaquina.get(i));
                }
                else{
                    etiquetasNumeros.get(i).setForeground(Color.RED);
                }
            }
        }
        else if(e.getSource() == mnuGuardar){ // Acciones a realizar al pulsar el menú guardar
            FrameSecundario panelDeControl = new FrameSecundario(this); // Declaro el formulario del panel de configuración
            panelDeControl.pack();
            panelDeControl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Y le digo que no haga nada al cerrarse para lanzar la confirmación
            panelDeControl.setVisible(true);


        }
        else if(e.getSource() == mnuRecords){
            FrameRecords ventanaRecords = new FrameRecords(this); // Declaro el formulario del panel de configuración
            ventanaRecords.pack();
            ventanaRecords.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Y le digo que no haga nada al cerrarse para lanzar la confirmación
            ventanaRecords.setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

    }
}