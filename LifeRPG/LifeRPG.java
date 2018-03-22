/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liferpg;

import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

/**
 *
 * @author g0050677
 */
public class LifeRPG extends JFrame{
    
    
    public LifeRPG(){
        
        player = new Jogador("ESCOLHA UM NOME");
        
        GroupLayout group = new GroupLayout(getContentPane());
        getContentPane().setLayout(group);
        group.setAutoCreateGaps(true);
        group.setAutoCreateContainerGaps(true);
        
        GroupLayout.ParallelGroup groupHorizontal = group.createParallelGroup();
        GroupLayout.SequentialGroup groupVertical = group.createSequentialGroup();
        
        JLabel nome = new JLabel();
        nome.setText(player.getNome());
        
        JLabel inf = new JLabel();
        inf.setText("Informações");
        //Funcoes do campo para digitar nome.
        JTextField campoNome = new JTextField("Digite seu nome", 10);
        limparCampo(campoNome);
        
        campoNome.addActionListener((ActionEvent e) -> {
            if(campoNome.getText() != null){
               player.setNome(campoNome.getText());
               nome.setText(player.getNome());
               group.replace(campoNome, nome);
            }
        });
        //Fim das funcoes do campo para digitar nome.
        
        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setName("");
        
        bar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                if(!bar.getName().equals("")){
                    player.addXp(bar.getName(), 1);
                    //bar.setString(string);
                    bar.setValue(player.getXp(bar.getName()));
                }
                //System.out.println(bar.getValue());
            }
        });
        
        
        JComboBox habilidades = new JComboBox();
        habilidades.setBounds(10, 10, 10, 10);
        adicionarLista(player, habilidades);

        habilidades.addItemListener((ItemEvent ie) -> {
            JComboBox cb = (JComboBox)ie.getSource();
            int indx = cb.getSelectedIndex();
            if(ie.getStateChange() == 1){
                inf.setText(player.getXSkillName(indx) + 
                        " | LVL: " + player.getXpNecessario(player.getXSkillName(indx)) +
                        " | XP: " + player.getXp(player.getXSkillName(indx)));
                
                bar.setValue(player.getXp(player.getXSkillName(indx)));
                bar.setName(player.getXSkillName(indx));
            }
        });
        
        
        JTextField addSkill = new JTextField("Digite a nova habilidade", 10);
        limparCampo(addSkill);
        addSkill.addActionListener((ActionEvent e) -> {
            if(addSkill.getText() != null){
                String skill = StringUtils.capitalize(addSkill.getText());
                player.setSkill(skill);
                adicionarLista(player, habilidades);
                addSkill.setText("");
            }
        });
        
        groupHorizontal
                .addGroup(group.createSequentialGroup()
                        .addComponent(campoNome))
                .addGroup(group.createSequentialGroup()
                        .addComponent(habilidades)
                        .addComponent(addSkill))
                .addGroup(group.createSequentialGroup()
                        .addComponent(bar))
                .addGroup(group.createSequentialGroup()
                        .addComponent(inf));
                
        group.setHorizontalGroup(groupHorizontal);
        
        groupVertical
                .addGroup(group.createParallelGroup()
                        .addComponent(campoNome))
                .addGroup(group.createParallelGroup()
                        .addComponent(habilidades)
                        .addComponent(addSkill))
                .addGroup(group.createParallelGroup()
                        .addComponent(bar))
                .addGroup(group.createParallelGroup()
                        .addComponent(inf));
                      
        group.setVerticalGroup(groupVertical);
        
        setTitle("Life RPG");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void limparCampo(JTextField e){
        e.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                e.setText("");
            }
        });
    }
    
    public static void adicionarLista(Jogador a, JComboBox c){
        List playerSkill = a.getAllSkillName();
        c.removeAllItems();
        for(int x = 0; x < playerSkill.size(); x++){
            c.addItem(playerSkill.get(x));
        }     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new LifeRPG().setVisible(true);
        });

    }
    
    private Jogador player;
    private JProgressBar barAnt;
}
