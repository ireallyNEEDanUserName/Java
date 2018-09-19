/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullonrpg;

/**
 *
 * @author g0050677
 */
public class Skill {
    
    private String skillName;
    private int skillLvl;
    private int skillXp;
    
    public Skill(String str){
        this.skillName = str;
        this.skillLvl = 1;
        this.skillXp = 0;
    }
    
    public String getSkillName(){
        return this.skillName;
    }
    
    public int getSkillLvl(){
        return this.skillLvl;
    }
    
    public int getSkillXp(){
        return this.skillXp;
    }
    public int getSkillNextXP(){
        int x = this.skillLvl * 2 - (this.skillLvl / 2) + 100;
        return x;
    }
    public void setSkillXp(int x){
        this.skillXp += x;
    }
}
