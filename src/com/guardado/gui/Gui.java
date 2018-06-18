package com.guardado.gui;

import com.guardado.threads.AnimalThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    private JPanel [] paneles;
    private JLabel [] labels;
    private JButton inicio;
    private String[] nombres = {"canguro","tortuga","dragon"};
    private JTextField hora;
    private JButton restart;

    public Gui(){
        super("Carrera de animales");
        initialComponents();

    }

    public void initialComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        paneles = new JPanel[3];
        labels = new JLabel[3];
        inicio = new JButton("Inicio");
        restart = new JButton("Restart");
        
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
        
        restart.setBounds(120, 0, 100, 30);
        container.add(restart);
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
        
        hora = new JTextField("Hora");
        hora.setBounds(500, 0, 200, 30);
        hora.setEditable(false);
        container.add(hora);

        
        setSize(700,650);
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
