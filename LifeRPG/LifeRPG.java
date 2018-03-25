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
import static javax.swing.GroupLayout.Alignment.CENTER;

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
        //group.setLayoutStyle(ls);
        
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
                    int barValue = addXp(bar.getName(), player, 1);
                    bar.setValue(barValue);
                    inf.setText(bar.getName() + 
                        " | LVL: " + player.getLvl(bar.getName()) +
                        " | XP: " + player.getXp(bar.getName()));
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
                        " | LVL: " + player.getLvl(player.getXSkillName(indx)) +
                        " | XP: " + player.getXp(player.getXSkillName(indx)));
                
                bar.setName(player.getXSkillName(indx));
                int barValue = addXp(bar.getName(), player, 0);
                bar.setValue(barValue);
            }
        });
        
        
        JButton addSkillBtn = new JButton("Add Skill");
        addSkillBtn.addActionListener((ActionEvent e) -> {
            newFrame(player, habilidades);
        });

        
        JButton mDez = new JButton("+10");
        mDez.addActionListener((ActionEvent e) -> {
            if(!"".equals(bar.getName())){
                int barValue = addXp(bar.getName(), player, 10);
                bar.setValue(barValue);
                inf.setText(bar.getName() +
                        " | LVL: " + player.getLvl(bar.getName()) +
                        " | XP: " + player.getXp(bar.getName()));
            }
            
        });
        
        groupHorizontal
                .addGroup(group.createSequentialGroup()
                        .addComponent(campoNome))
                .addGroup(group.createSequentialGroup()
                        .addComponent(habilidades, 0, GroupLayout.PREFERRED_SIZE, 150)
                        .addComponent(addSkillBtn))
                .addGroup(group.createSequentialGroup()
                        .addComponent(bar)
                        .addComponent(mDez))
                .addGroup(group.createSequentialGroup()
                        .addComponent(inf));
                
        group.setHorizontalGroup(groupHorizontal);
        
        groupVertical
                .addGroup(group.createParallelGroup()
                        .addComponent(campoNome, GroupLayout.Alignment.CENTER))
                .addGroup(group.createParallelGroup()
                        .addComponent(habilidades, 0, GroupLayout.PREFERRED_SIZE, 40)
                        .addComponent(addSkillBtn, GroupLayout.Alignment.CENTER))
                .addGroup(group.createParallelGroup()
                        .addComponent(bar)
                        .addComponent(mDez, GroupLayout.Alignment.CENTER))
                .addGroup(group.createParallelGroup()
                        .addComponent(inf));
                      
        group.setVerticalGroup(groupVertical);
        
        setTitle("Life RPG");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void newFrame(Jogador player, JComboBox habilidades){
        JFrame frame = new JFrame("Add Skill");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(200, 100));
        frame.setVisible(true);
        
        JTextField addSkill = new JTextField("", 10);
        limparCampo(addSkill);
        addSkill.addActionListener((ActionEvent e) -> {
            if(addSkill.getText() != null){
                String skill = StringUtils.capitalize(addSkill.getText());
                player.setSkill(skill);
                adicionarLista(player, habilidades);
                addSkill.setText("");
                frame.dispose();
            }
        });
        
        frame.add(addSkill);
        
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
    
    public static int addXp(String nome, Jogador player, int add){
        
        player.addXp(nome, add);
        int xpNecessario = player.lvlUp(player.getLvl(nome));
        int barValue = (int) (player.getXp(nome) / ((float) xpNecessario / (float) 100));
        
        return barValue;      
    }
    
    private Jogador player;
    private JProgressBar barAnt;
}
