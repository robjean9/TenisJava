/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogo;

/**
 *
 * @author fcsa
 */
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class bola {
    private int altura = 20;
    private int largura = 20;
    private Image imagem = new  ImageIcon(getClass().getResource("/imagens/bola.gif")).getImage().getScaledInstance(getLargura(),getAltura(), 1);
    private double x;
    private double y;
    private double z;
    private double dx = 6;
    private double dy = new Random().nextDouble() * 10 - 2.5;
    public bola (int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle getLimites(){
        return new Rectangle((int) x, (int) y, largura, altura);
    }

    public void mexer() {
        x += dx;
        y += dy;
    }

    public double getX() {
        return x;
    }
    public void  setX(double x){
        this.x = x;
    }
    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }
    public double getDx(){
        return dx;
    }
    public void setDx(double dx){
        this.dx = dx;
    }
    public double getDy(){
        return dy;
    }
    public void setDy(double dy){
        this.dy = dy;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the largura
     */
    public int getLargura() {
        return largura;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * @return the imagem
     */
    public Image getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
    
}
