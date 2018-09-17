/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullonrpg;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author g0050677
 */
public class FullOnRPG {
    
    public static Jogador player;
    private static JFrame inicialFrame;
    private static JFrame mainFrame;
    private static Frames frames;
    
    public FullOnRPG(){
        
        frames = new Frames();
        player = new Jogador("Nome");
        frames.mainFrame();
        inicialFrame = frames.inicial();
        
    };
    
    public static void main(String[] args) {
        
        
        new FullOnRPG();
        inicialFrame.setVisible(true);

        
    }
    
    //FUNÇÃO QUE PEGA O NOME DO JOGADOR
    public static void mainStart(JTextField field, ActionEvent e){
        
        Component component = (Component) e.getSource();
        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        
        if(!(field.getText().equals(""))){
            frame.setVisible(false);
            frames.getJanela().setVisible(true);
            player.setNome(field.getText());
            frames.setLabelText(player.getNome());
        }else{
            JFrame popup = new JFrame();
            popup.setSize(100, 100);
            JLabel label = new JLabel("DIGITE UM NOME!!");
            label.setForeground(Color.RED);
            popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popup.add(label);
            popup.setVisible(true);
        }
        
        
    };
    
    
    
    
}
