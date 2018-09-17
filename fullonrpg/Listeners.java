/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullonrpg;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author G0050677
 */
public class Listeners implements ActionListener {

    private Frames frames;
    private Menu menu;
    
    public Listeners(Frames frames){
        frames = this.frames;
        menu = frames.getMenu();
    };
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
       menu.addActionListener((ActionEvent e) ->{
           System.out.print(e.toString());
       });
    
    }
    
}
