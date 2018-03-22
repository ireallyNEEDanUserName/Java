/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liferpg;

/**
 *
 * @author g0050677
 */
public class Jogador extends Skill{
    
    private String nome;
    
    Jogador(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
       
    
}
