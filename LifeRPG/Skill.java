/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liferpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JProgressBar;

/**
 *
 * @author g0050677
 */
public class Skill {
    
    private String skillName;
    private List skillColection = new ArrayList();  
    private Map<String, Integer> skillXp = new HashMap<>();
    private Map<String, Integer> skillLv = new HashMap<>();
    private Map<String, JProgressBar> progBar = new HashMap<>();
    
    public void setSkill(String str){
        
        if(!skillColection.contains(str)){
            this.skillName = str;
            this.skillColection.add(str);
            this.skillXp.put(str, 0);
            this.skillLv.put(str, 1);
            this.progBar.put(str, new JProgressBar());
        }
        
    }
    
    public List getAllSkillName(){
        return this.skillColection;
    }
    
    public String getXSkillName(int x){
        return this.skillColection.get(x).toString();
    }
    
    public int getLvl(String str){
        return this.skillLv.get(str);
    }
    
    public void addXp(String str, int v){
        int x = this.skillXp.get(str);
        x += v;
        this.skillXp.put(str, x);
        int xpNecessario = lvlUp(this.skillLv.get(str));
        if(this.skillXp.get(str) >= xpNecessario){
            this.skillLv.put(str, this.skillLv.get(str) + 1);
            this.skillXp.put(str, this.skillXp.get(str) - xpNecessario);
        }
    }
    
    public int getXp(String str){
        return this.skillXp.get(str);
    }
    
    public int getXpNecessario(String str){
        return this.skillLv.get(str) * ( 5 ^ this.skillLv.get(str));
    }
    
    public JProgressBar getBar(String str){
        return this.progBar.get(str);
    }
    
    public int lvlUp(int lvl){
        return (int) Math.pow(2 , lvl);
    }
}
