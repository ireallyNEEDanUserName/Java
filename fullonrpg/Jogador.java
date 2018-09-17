/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullonrpg;

import java.util.ArrayList;

/**
 *
 * @author g0050677
 */
public class Jogador{
    
    private String nome;
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    
    Jogador(String nome){
        this.nome = nome;
        setSkill("Mining");
        setSkill("Smithing");
    }
    
    private void setSkill(String str){
        this.skills.add(new Skill(str));
    }
    
    public ArrayList getSkills(){
        return this.skills;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
       
    
}
