/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 *
 * @author ADRIANA
 */
public class SplashScreen extends javax.swing.JFrame {

    /**
     * Creates new form SplashScreen
     */
    public SplashScreen() {
        setUndecorated(true);
        initComponents();
        initUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void initUI() {
        setSize(600, 400);
        setLocationRelativeTo(null);

        URL imageUrl = getClass().getResource("/Img/imagenDeInicio.jpg");
        if (imageUrl == null) {
            System.err.println("No se encontró la imagen!");
            return;
        }
        ImageIcon icon = new ImageIcon(imageUrl);

        // Escalar la imagen al tamaño del JFrame
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel label = new JLabel(scaledIcon);
        label.setSize(600, 400);

        // Cambiamos el layout a null para poder controlar tamaños y posiciones manualmente
        setLayout(null);
        add(label);

        // Posicionamos el label en 0,0 para que cubra todo
        label.setBounds(0, 0, 600, 400);

        Timer timer = new Timer(3000, e -> {
            new Login().setVisible(true);
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }



    
    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            SplashScreen splash = new SplashScreen();
            splash.setVisible(true);
        });
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
