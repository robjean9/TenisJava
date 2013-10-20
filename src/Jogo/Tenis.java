/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import javax.swing.JFrame;

/**
 *
 * @author robson
 */
public class Tenis extends JFrame{
    private int vBol;
    private int vRaq;
    public Tenis(){
        setTitle("Tenis v1.0");
        setSize(1000, 733);
        add(new Campo(1000,733));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
            
    public static void main(String[] args){
        new Tenis();
    }

    /**
     * @return the vBol
     */
    public int getvBol() {
        return vBol;
    }

    /**
     * @param vBol the vBol to set
     */
    public void setvBol(int vBol) {
        this.vBol = vBol;
    }

    /**
     * @return the vRaq
     */
    public int getvRaq() {
        return vRaq;
    }

    /**
     * @param vRaq the vRaq to set
     */
    public void setvRaq(int vRaq) {
        this.vRaq = vRaq;
    }
}
