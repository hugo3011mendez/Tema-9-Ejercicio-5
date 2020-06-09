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
            array[i] = (int)(Math.random()*50+1);
            for(int j = 0 ; j< array.length; j++){
                if(i != j && array[i] == array[j]){ // Si hay algún número repetido en el array, se sustituirá por otro número aleatorio diferente
                    array[j] = (int)(Math.random()*50+1);
                }
            }
        }
    }

    JButton btnJugar;

    JMenuBar menu;
    JMenu mnuMenu;
    JMenuItem mnuGuardar, mnuRecords;

    Timer temporizador;

    int xCheck = 30, yCheck = 10, xLabel = 150, yLabel = 185; // Creo variables para gestionar las coordenadas de los CheckBox y los Label cuando se generan dinámicamente
    int numsEscogidos = 0; // Creo una variable para controlar que se han escogido 6 números
    ArrayList<JCheckBox> checkNumeros = new ArrayList<>(); // Creo una colección donde guardar los checkbox de los números
    int[] numsDeLaMaquina = new int[6]; // Creo una colección para guardar los números que elige aleatoriamente la máquina
    ArrayList<Integer> numsSeleccionados = new ArrayList<>(); // Creo otra colección que guardará los números seleccionados
    ArrayList<Integer> numsAcertados = new ArrayList<>(); // Creo una colección para guardar los números acertados en el archivo al mostrarse la nueva ventana
    ArrayList<JLabel> etiquetasNumeros = new ArrayList<>(); // Creo una colección para los números que se mostrarán en la lotería

    // Creo variables para gestionar cómo se muestra el título de la ventana
    String titulo = "Lotería";
    int letraTitulo = 0;    

    public FrameEjercicio5(){
        super("");
        setLayout(null);

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

        for (int i = 1; i <= 49; i++) {
            JCheckBox chkNumero = new JCheckBox(String.valueOf(i)); // Creo un checkbox por cada número
            chkNumero.setSize(chkNumero.getPreferredSize());
            chkNumero.setLocation(xCheck, yCheck);
            chkNumero.addItemListener(this);
            this.add(chkNumero);
            checkNumeros.add(chkNumero);

            if(i % 10 == 0){
                yCheck += 20;
                xCheck = 30;
            }
            else{
                xCheck += 40;
            }
        }

        btnJugar = new JButton("Jugar");
        btnJugar.setSize(btnJugar.getPreferredSize());
        btnJugar.setLocation(175, 150);
        btnJugar.setEnabled(false);
        btnJugar.addActionListener(this);
        add(btnJugar);

        for (int i = 0; i < 6; i++) {
            JLabel lblNum = new JLabel();
            lblNum.setLocation(xLabel, yLabel);
            add(lblNum);

            etiquetasNumeros.add(lblNum);

            xLabel += 20;
        }

        // Empiezo el contador cuando se abra el formulario
        temporizador = new Timer(300, this);
        temporizador.start();     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnJugar){ // Acciones a realizar cuando se pulse el botón de jugar

            llenarArray(numsDeLaMaquina);

            // numsDeLaMaquina[0] = 4;
            // numsDeLaMaquina[1] = 44;
            // numsDeLaMaquina[2] = 30;
            // numsDeLaMaquina[3] = 22;
            // numsDeLaMaquina[4] = 7;
            // numsDeLaMaquina[5] = 1;

            boolean acertado = false; // Creo una variable para controlar si el número ha sido acertado
            for (int i = 0; i < numsDeLaMaquina.length; i++) {
                acertado = false;

                for (int j = 0; j < numsSeleccionados.size(); j++) {

                    if(numsDeLaMaquina[i] == numsSeleccionados.get(j)){
                        acertado = true;
                        checkNumeros.get(numsSeleccionados.get(j)-1).setForeground(Color.GREEN);
                    }
                    else{
                        if(!acertado){
                            checkNumeros.get(numsSeleccionados.get(j)-1).setForeground(Color.RED);
                        }
                    }

                }

                // Introduzco el número en la etiqueta
                etiquetasNumeros.get(i).setText(String.valueOf(numsDeLaMaquina[i]));
                etiquetasNumeros.get(i).setSize(etiquetasNumeros.get(i).getPreferredSize());
                if(acertado){
                    etiquetasNumeros.get(i).setForeground(Color.GREEN);
                    numsAcertados.add(numsDeLaMaquina[i]);
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
        else if(e.getSource() == temporizador){ // Voy añadiendo la palabra letra por letra al título
            if(letraTitulo == titulo.length()){
                letraTitulo = 0;
                setTitle("");
            }

            setTitle(getTitle() + String.valueOf(titulo.charAt(letraTitulo)));
            letraTitulo ++;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(((JCheckBox)e.getSource()).isSelected()){
            numsEscogidos ++;
            numsSeleccionados.add(Integer.parseInt(((JCheckBox)e.getSource()).getText()));
        }
        else{
            numsEscogidos --;
            numsSeleccionados.remove(numsSeleccionados.indexOf(Integer.parseInt(((JCheckBox)e.getSource()).getText())));
        }
        
        
        if(numsEscogidos == 6){ // Si hay 6 números seleccionados activo el botón, en caso contrario lo desactivo
            btnJugar.setEnabled(true);
        }
        else{
            btnJugar.setEnabled(false);
        }
    }
}