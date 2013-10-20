/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogo;

/**
 *
 * @author fcsa
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Campo extends JPanel implements ActionListener {
    private bola bola;
    private Raquete raquetePc;
    private Raquete raqueteJ;
    private Timer t;
    private Image fundo;
    private int pontoJ = 0;
    private int pontoPC = 0;
    private String msg = "";
    private int VelocityBol;
    private int VelocityRaquete;
    public Campo(int largura, final int altura){
        setFocusable(true);
        setDoubleBuffered(true);
        setSize(largura, altura);
        fundo = new ImageIcon(this.getClass().getResource("/imagens/campo.png")).getImage().getScaledInstance(largura, altura , 1);
        inicializa();
        t = new Timer(7, this);
        t.start();
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int tecla = e.getKeyCode();
                System.out.println(tecla);
                if(tecla == KeyEvent.VK_UP){
                    getRaqueteJ().setDy(-3);
                }
                if(tecla == KeyEvent.VK_DOWN){
                    getRaqueteJ().setDy(3);
                }

                if(tecla == KeyEvent.VK_W){
                    getRaquetePc().setDy(-3);
                }
                if(tecla == KeyEvent.VK_S){
                    getRaquetePc().setDy(3);
                }


                if(tecla == 'p' || tecla == 'P'){
                    if(getT().isRunning()){
                        setMsg("Pausa");
                        repaint();
                        getT().stop();
                    } else {
                        getT().restart();
                        setMsg("");
                    }
                }
                if(tecla == 'r' || tecla == 'R'){
                    inicializa();
                    t.start();
                }
                if(tecla == KeyEvent.VK_SPACE && getPontoJ()<5 && getPontoPC()<5){
                    setMsg("");
                    getT().start();
                }
            }
            public void keyReleased(KeyEvent e){
                getRaqueteJ().setDy(0);
                getRaquetePc().setDy(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e){
        getBola().mexer();
        getRaquetePc().mexer();
        if(getRaquetePc().getY()>this.getHeight()-getRaquetePc().getAltura())getRaquetePc().setY(this.getHeight()-getRaquetePc().getAltura());
        if(getRaquetePc().getY()<0)getRaquetePc().setY(0);
        getRaqueteJ().mexer();
        if(getRaqueteJ().getY()>this.getHeight()-getRaqueteJ().getAltura())getRaqueteJ().setY(this.getHeight()-getRaqueteJ().getAltura());
        if(getRaqueteJ().getY()<0)getRaqueteJ().setY(0);

        if(verificaColisao(getBola(), getRaqueteJ())){
            getBola().setDx(-getVelocityBol());
            getBola().setDy(new Random().nextDouble() * Math.signum(getBola().getDy() * 10));

        }
        if(verificaColisao(getBola(), getRaquetePc())){
             getBola().setDx(getVelocityBol());
            getBola().setDy(new Random().nextDouble() * Math.signum(getBola().getDy() * 10));
            }
        if(getBola().getY()< 10 || getBola().getY() > this.getHeight() - getBola().getAltura()){
            getBola().setDy(-bola.getDy());

        }
        //mexerPc();
        verificaPonto();
        repaint();
    }

    private void inicializa(){
        setRaquetePc(new Raquete(10, this.getHeight() / 2));
        setRaqueteJ(new Raquete(this.getWidth() - 60, this.getHeight() / 2));
        setBola(new bola(this.getWidth(), this.getHeight() / 2));
        setPontoJ(0);
        setPontoPC(0);
        setMsg("");
    }

    private void verificaPonto(){
        if(getBola().getX()< 0){
            getBola().setX(getRaqueteJ().getX() - getRaqueteJ().getLargura());
            getBola().setY(getRaqueteJ().getY());
            getBola().setDy(new Random().nextDouble() * Math.signum(getBola().getDy() * 5));
            getBola().setDx(-getVelocityBol());
            setPontoJ(getPontoJ() + 1);
            getT().stop();
            setBola(new bola(this.getWidth()/2, this.getHeight() / 2));
            setMsg("Ponto para Jogador - Espaço para Continuar");
        }
        if(getBola().getX() > this.getWidth()){
            getBola().setX(getRaquetePc().getX() + getRaquetePc().getLargura());
            getBola().setY(getRaquetePc().getY());
            getBola().setDy(new Random().nextDouble() * Math.signum(getBola().getDy() * 5));
             getBola().setDx(getVelocityBol());
            setPontoPC(getPontoPC() + 1);
            getT().stop();
             setBola(new bola(this.getWidth()/2 , this.getHeight() / 2));
            setMsg("Ponto para PC - Espaço para Continuar");
        }
        if(getPontoJ() > 4){
            setMsg("Jogador Ganhou! 'R' - Reinicia o Jogo");
            getT().stop();
            repaint();
        }
        
        if(getPontoPC() > 4){
            setMsg("Pc Ganhou! 'R' - Reinicia o Jogo");
            getT().stop();
            repaint();
        }
    }
   /* private void mexerPc(){
        if(getBola().getX() < this.getWidth() /2 && getBola().getDx() < 0){
            getRaquetePc().setDy((2.5 * Math.signum((int) (getBola().getY() - getRaquetePc().getY()))));
        } else {
            getRaquetePc().setDy(0);
        }
    }*/
    private boolean verificaColisao(bola b, Raquete r){
        if( r.getLimites().intersects(b.getLimites())){
            return true;
        } else {
            return false;
        }
    }
    @Override
    
    public void paint(Graphics g) {
        g.drawImage(getFundo(), 0, 0, null);
        g.setColor(Color.white);
        g.drawLine(this.getWidth() / 2, 0 ,this.getWidth() /2, this.getHeight());
        g.drawImage(getBola().getImagem(), (int) getBola().getX(), (int) getBola().getY(), this);
        g.drawImage(getRaquetePc().getImagem(), (int) getRaquetePc().getX(), (int) getRaquetePc().getY(), this);
        g.drawImage(getRaqueteJ().getImagem(), (int) getRaqueteJ().getX(), (int) getRaqueteJ().getY(), this);
        g.setFont(new Font("Arial", 0, 18));
        
        g.drawString(getPontoPC() + " ", this.getWidth() / 4, 50);
        g.drawString(getPontoJ() + " ", 3 * (this.getWidth() /4) ,50);
        Font f = new Font("Arial", Font.BOLD, 20);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics(f);
        Rectangle2D rect = fm.getStringBounds(getMsg(),g);
        int textWidth = (int) (rect.getWidth());
        int panelWidth = this.getWidth();
        int x = (panelWidth - textWidth) / 2;
        g.drawString(getMsg(),x, 3 * (this.getHeight() / 4));
    }

    /**
     * @return the bola
     */
    public bola getBola() {
        return bola;
    }

    /**
     * @param bola the bola to set
     */
    public void setBola(bola bola) {
        this.bola = bola;
    }

    /**
     * @return the raquetePc
     */
    public Raquete getRaquetePc() {
        return raquetePc;
    }

    /**
     * @param raquetePc the raquetePc to set
     */
    public void setRaquetePc(Raquete raquetePc) {
        this.raquetePc = raquetePc;
    }

    /**
     * @return the raqueteJ
     */
    public Raquete getRaqueteJ() {
        return raqueteJ;
    }

    /**
     * @param raqueteJ the raqueteJ to set
     */
    public void setRaqueteJ(Raquete raqueteJ) {
        this.raqueteJ = raqueteJ;
    }

    /**
     * @return the t
     */
    public Timer getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Timer t) {
        this.t = t;
    }

    /**
     * @return the fundo
     */
    public Image getFundo() {
        return fundo;
    }

    /**
     * @param fundo the fundo to set
     */
    public void setFundo(Image fundo) {
        this.fundo = fundo;
    }

    /**
     * @return the pontoJ
     */
    public int getPontoJ() {
        return pontoJ;
    }

    /**
     * @param pontoJ the pontoJ to set
     */
    public void setPontoJ(int pontoJ) {
        this.pontoJ = pontoJ;
    }

    /**
     * @return the pontoPC
     */
    public int getPontoPC() {
        return pontoPC;
    }

    /**
     * @param pontoPC the pontoPC to set
     */
    public void setPontoPC(int pontoPC) {
        this.pontoPC = pontoPC;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the VelocityBol
     */
    public int getVelocityBol() {
        return VelocityBol;
    }

    /**
     * @param VelocityBol the VelocityBol to set
     */
    public void setVelocityBol(int VelocityBol) {
        this.VelocityBol = VelocityBol;
    }

    /**
     * @return the VelocityRaquete
     */
    public int getVelocityRaquete() {
        return VelocityRaquete;
    }

    /**
     * @param VelocityRaquete the VelocityRaquete to set
     */
    public void setVelocityRaquete(int VelocityRaquete) {
        this.VelocityRaquete = VelocityRaquete;
    }
}
