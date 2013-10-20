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
import javax.swing.ImageIcon;

public class Raquete {
    private int altura = 70;
    private int largura = 50;
    private Image imagem = new ImageIcon(getClass().getResource("/imagens/raquete.gif")).getImage().getScaledInstance(getLargura(), getAltura(), 1);
    private double x;
    private double y;
    private double z;
    private double dy = 0;
    public Rectangle getLimites(){
        return new Rectangle((int) getX(), (int) getY(), getLargura(), getAltura());
    }

    public Raquete(double x, double y){
        this.x = x;
        this.y = y;

    }

    public void mexer(){
        setY(getY() + getDy());
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

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public double getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param dy the dy to set
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    
}
