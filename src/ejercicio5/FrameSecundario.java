package ejercicio5;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import jdk.internal.org.objectweb.asm.tree.analysis.Frame;

public class FrameSecundario extends JDialog implements ActionListener {
    JLabel lblNombre;
    JTextField txfNombre;
    JButton btnGuardar;

    // Creo una variable para que el archivo se sitúe en la carpeta del usuario
    File archivoLoteria = new File(System.getProperty("user.home") + System.getProperty("file.separator") +  ".records.txt");

    public FrameSecundario(FrameEjercicio5 panelDeControl) {
        super(panelDeControl, true);
        setLayout(new FlowLayout());
        setTitle("Guardar en archivo");

        lblNombre = new JLabel("Escribe aquí tu nombre : ");
        lblNombre.setSize(lblNombre.getPreferredSize());
        add(lblNombre);

        txfNombre = new JTextField(20);
        txfNombre.setSize(txfNombre.getPreferredSize());
        add(txfNombre);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setSize(btnGuardar.getPreferredSize());
        btnGuardar.addActionListener(this);
        add(btnGuardar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FrameEjercicio5 panelDeControl = (FrameEjercicio5)this.getOwner(); // Declaro el formulario principal del ejercicio para poder trabajar con él

        if(e.getSource() == btnGuardar){
            if(!archivoLoteria.exists()){ // Si el archivo de lotería no existe, lo creo
                try (PrintWriter f = new PrintWriter(archivoLoteria)){
                        
                } catch (Exception e1) {
                    System.err.println("Error al crear el archivo de operaciones");
                }
            }

            // Ahora procedo a guardar el nombre y los números acertados en el archivo
            try (PrintWriter escribir = new PrintWriter(new FileWriter(archivoLoteria, true))){
                escribir.println(txfNombre.getText() + ":" + panelDeControl.numsAcertados.size());
            } catch (IOException e2) {
                System.err.println("Error de acceso al archivo de operaciones");
            }
        }
    }
}