package com.guardado.gui;

import com.guardado.threads.AnimalThread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.*;

public class Gui extends JFrame {

    private JPanel [] paneles;
    private JLabel [] labels;
    private JButton inicio;
    private String[] nombres = {"canguro","tortuga","dragon"};
    private JLabel mostrarhora;
    private JButton restart;

    public Gui(){
        super("Carrera de animales");
        initialComponents();
        time();
    }

    public void initialComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        paneles = new JPanel[3];
        labels = new JLabel[3];
        inicio = new JButton("Inicio");
        restart = new JButton("Restart");
        mostrarhora = new JLabel();
        
        Container container = getContentPane();
        //llenando el panel
        for (int i=0 ; i < 3 ; i++)
        {
            labels[i] = new JLabel();
            labels[i].setIcon(new ImageIcon(getClass().getResource(nombres[i]+".gif")));
            labels[i].setBounds(10,(i*220)+10,200,200);
            container.add(labels[i]);
        }
        inicio.setBounds(10,0,100,30);
        container.add(inicio);
        restart.setBounds(120, 0, 100, 30);
        container.add(restart);
        mostrarhora.setBounds(500, 0, 200, 30);
        container.add(mostrarhora);
        
        inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnimalThread canguro = new AnimalThread("canguro",labels[0].getX(),labels[0].getY(),510,labels[0]);
                AnimalThread tortuga = new AnimalThread("colibri",labels[1].getX(),labels[1].getY(),510,labels[1]);
                AnimalThread dragon = new AnimalThread("dragon",labels[2].getX(),labels[2].getY(),510,labels[2]);
                canguro.start();
                tortuga.start();
                dragon.start();
                if(canguro.isAlive()){
                    restart.setVisible(true);
                }

            }
        });
        
        
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0 ; i < 3 ; i++){
                labels[i].setIcon(new ImageIcon(getClass().getResource(nombres[i]+".gif")));
                labels[i].setBounds(10,(i*220)+10,200,200);
                container.add(labels[i]);
                }
                    restart.setVisible(false);
                }
        });
        restart.setVisible(false);

        setSize(700,650);
    }
    
    public void time() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar calendario = new GregorianCalendar();
                        int hora = calendario.get(Calendar.HOUR);
                        int min = calendario.get(Calendar.MINUTE);
                        int seg = calendario.get(Calendar.SECOND);
                        String ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM" :"PM";
                        System.out.println();
                        mostrarhora.setText("Hora: "+hora+":"+min+":"+seg+" "+ampm);
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

}
