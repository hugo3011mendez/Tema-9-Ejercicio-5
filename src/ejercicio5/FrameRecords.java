package ejercicio5;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class FrameRecords extends JDialog implements ActionListener {
    JTextArea txaRecords;
    JScrollPane scroll;
    Timer temporizador;

    File archivoLoteria = new File(System.getProperty("user.home") + System.getProperty("file.separator") + ".records.txt");

    // Creo variables para gestionar cómo se muestra el título de la ventana
    String titulo = "Records";
    int letraTitulo = 0;

    public FrameRecords(FrameEjercicio5 ventanaRecords) {
        super(ventanaRecords, true);
        setLayout(new FlowLayout());
        setTitle("");

        txaRecords = new JTextArea(20, 20);
        scroll = new JScrollPane(txaRecords); // Creo una barra de scroll para el textarea
        add(scroll);

        if (!archivoLoteria.exists()) {
            JOptionPane.showMessageDialog(null, "Error! El archivo de récords no existe!", "Error al leer del archivo",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String texto = ""; // Creo variable de texto para el texto de la línea del archivo
            try (Scanner mostrarArchivo = new Scanner(archivoLoteria)) { // Hago un try-with-resources para leer el archivo
                while (mostrarArchivo.hasNext()) {
                    texto = mostrarArchivo.nextLine();

                    txaRecords.append("Nombre : " + texto.split(":")[0] + "  Aciertos : " + texto.split(":")[1] + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de acceso al archivo de lotería!",
                        "Error al leer del archivo", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Le doy un valor inicial a los minutos y los segundos, y empiezo el contador cuando se abra el formulario
        temporizador = new Timer(300, this);
        temporizador.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == temporizador){ // Voy añadiendo la palabra letra por letra al título
            if(letraTitulo == titulo.length()){
                letraTitulo = 0;
                setTitle("");
            }

            setTitle(getTitle() + String.valueOf(titulo.charAt(letraTitulo)));
            letraTitulo ++;
        }
    }
}