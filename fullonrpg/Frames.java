/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullonrpg;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author g0050677
 */
public class Frames extends JFrame {

    public Frames(){
        constInicial();
    };
    
    public JFrame inicial(){
        
        JFrame frame = new JFrame("Escolha de nome");
        frame.setSize(320,80);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        JPanel painel = new JPanel();
        JLabel label = new JLabel("Digite seu nome: ");
        JTextField box = new JTextField(10);
        JButton btn = new JButton("Enviar");
        
        btn.addActionListener((ActionEvent e) ->{
            FullOnRPG.mainStart(box, e);
        });
        
        painel.add(label);
        painel.add(box);
        painel.add(btn);
        
        frame.add(painel);
        
        return frame;
    };
    
/*********************COMEÇA FUNÇÕES DA JANELA PRINCIPAL.**********************/
    private JFrame janela = new JFrame("Main"); 
    private JLabel label = new JLabel("Label");
    private MenuBar menuBar = new MenuBar();
    private Menu menu = new Menu("Geral");
    
    private void constInicial(){
        
        janela.setSize(300,300);
        janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        menu.add("Main");
        menu.add("Skills");
        
        menuBar.add(menu);
        janela.setMenuBar(menuBar);
        
        menu.addActionListener((ActionEvent e) ->{
            System.out.println(e.getActionCommand());
            janela.getContentPane().removeAll();
            if(e.getActionCommand().equals("Skills")) skillsFrame();
            if(e.getActionCommand().equals("Main")) mainFrame();
            janela.revalidate();
        });
    };
    
    public JFrame getJanela(){
        return janela;
    };
    
    public void setLabelText(String str){
        this.label.setText(str);
    };
    
    public Menu getMenu(){
        return menu;
    };
    
    public void mainFrame(){
        
        JPanel painel = new JPanel();
        painel.setBackground(java.awt.Color.black);
        
        label.setForeground(Color.WHITE);
        label.setText(FullOnRPG.player.getNome());
        painel.add(label);
        janela.add(painel);
    };
    
    public void skillsFrame(){
        
        //System.out.print("skFrm");
        
        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        
        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.ParallelGroup horizontal = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();
        
        label.setText("Skills");
        label.setForeground(Color.BLACK);
        
        horizontal.addGroup(layout.createParallelGroup()
                        .addComponent(label));
        vertical.addGroup(layout.createParallelGroup()
                        .addComponent(label));
        
        
        ArrayList skl = FullOnRPG.player.getSkills();
        
        skl.forEach((Object skill) -> {
            
            Skill e = (Skill) skill;
           
            JLabel skillName = new JLabel();
            JLabel skillLV = new JLabel();
            JLabel skillXP = new JLabel();
            skillName.setForeground(Color.BLACK);
            skillLV.setForeground(Color.BLACK);
            skillXP.setForeground(Color.BLACK);
            
            skillName.setText(e.getSkillName());
            skillLV.setText(
                    String.valueOf("LV: "
                    .concat(String.valueOf(e.getSkillLvl()))));
            skillXP.setText(
                    String.valueOf(e.getSkillXp())
                    .concat(" / ")
                    .concat(String.valueOf(e.getSkillNextXP())));
            
            horizontal.addGroup(
                layout.createSequentialGroup()
                    .addComponent(skillName)
                    .addComponent(skillLV)
                    .addComponent(skillXP)    
            );
            vertical.addGroup(
                layout.createParallelGroup()
                    .addComponent(skillName)
                    .addComponent(skillLV)
                    .addComponent(skillXP) 
            );
        });

        
        layout.setHorizontalGroup(horizontal);
        layout.setVerticalGroup(vertical);
        janela.add(painel);
    };
    
}
