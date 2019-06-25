package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Game extends JFrame {


    private ImageIcon cabeca = new ImageIcon(getClass().getResource("cabeca.png"));
    private JLabel lCabeca = new JLabel(cabeca);
    private ImageIcon maca = new ImageIcon(getClass().getResource("apple.png"));
    private JLabel lMaca = new JLabel(maca);
    private ImageIcon corpo = new ImageIcon(getClass().getResource("corpo.png"));
    private JLabel lCorpo = new JLabel(corpo);
    private int macaX;
    private int macaY;
    private int cabecaX;
    private int cabecaY;
    private int corpoX;
    private int corpoY;
    private int tamanhoCorpo = 0;
    private Fila fila = new Fila();
    private int velSleep = 100;

    public Game () {
        new Movimento().start();
        inicio();
        editarComponentes();
    }

    public boolean colisao() {
        if ((cabecaX >= 680) || (cabecaX <= -10) || (cabecaY >= 460) || (cabecaY <= -10)) {
            JOptionPane.showMessageDialog(null, "Há-Há você é muito ruim");
            return true;
        }
        return false;
    }

    public class Movimento extends Thread {
        private boolean cima = false;
        private boolean baixo = false;
        private boolean esquerda = false;
        private boolean direita = false;

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(velSleep);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                if (fila.size() == 10) {
                    velSleep = 80;
                } else {
                    if (fila.size() == 20){
                        velSleep = 70;
                    } else {
                        if (fila.size() == 30) {
                            velSleep = 60;
                        } else{
                            if (fila.size() == 40) {
                                velSleep = 50;
                            } else {
                                if (fila.size() == 50) {
                                    velSleep = 40;
                                }
                            }
                        }

                    }
                }

                if (verificaMaca()) {
                    int auxCabecaX = lCabeca.getX();
                    int auxCabecaY = lCabeca.getY();
                    Node nodeTes = new Node();
                    nodeTes.getValor().setBounds(auxCabecaX, auxCabecaY, 20, 20);
                    fila.enfileirar(nodeTes);
                    add(nodeTes.getValor());
                    editarMaca();
                }

                addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)) && (!baixo)) {
                            cima = true;
                            baixo = false;
                            esquerda = false;
                            direita = false;
                        }
                        if (((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)) && (!cima)) {
                            baixo = true;
                            cima = false;
                            esquerda = false;
                            direita = false;
                        }
                        if (((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A)) && (!direita)) {
                            esquerda = true;
                            cima = false;
                            baixo = false;
                            direita = false;
                        }
                        if (((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)) && (!esquerda)) {
                            direita = true;
                            esquerda = false;
                            baixo = false;
                            cima = false;
                        }

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

                if (cima) {
                    cabecaY -= 20;
                    corpoY  -= 20;
                }
                if (baixo) {
                    cabecaY += 20;
                    corpoY  += 20;
                }
                if (esquerda) {
                    cabecaX -= 20;
                    corpoX  -= 20;

                }
                if (direita) {
                    cabecaX += 20;
                    corpoX  += 20;
                }
                if (!colisao()) {
                    lCabeca.setBounds(cabecaX, cabecaY, 20, 20);
                    int auxCabecaX = cabecaX;
                    int auxCabecaY = cabecaY;

                    if (direita) {

                        corpoX = cabecaX - 20;
                        corpoY = cabecaY;
                    }
                    if (esquerda) {
                        corpoX = cabecaX + 20;
                        corpoY = cabecaY;
                    }
                    if (cima) {
                        corpoY = cabecaY + 20;
                        corpoX = cabecaX;
                    }
                    if (baixo) {
                        corpoY = cabecaY - 20;
                        corpoX = cabecaX;
                    }

                    if (fila.size() > 0) {
                        Node aux = fila.getInicio();
                        int auxFimX = aux.getValor().getX();
                        int auxFimY = aux.getValor().getY();
                        aux.getValor().setBounds(corpoX, corpoY, 20, 20);
                        while (aux.getProximo() != null) {
                            if (aux.getProximo() != null) {
                                int auxxFinalX = aux.getProximo().getValor().getX();
                                int auxxFinalY = aux.getProximo().getValor().getY();
                                aux.getProximo().getValor().setBounds(auxFimX, auxFimY, 20, 20);
                                aux = aux.getProximo();

                                if ((cabecaY == auxFimY && cabecaX == auxFimX && fila.size() != 0)) {
                                    JOptionPane.showMessageDialog(null, "Há-Há você é muito ruim");
                                    System.exit(0);
                                }
                                auxFimX = auxxFinalX;
                                auxFimY = auxxFinalY;
                                if (direita && corpoX == cabecaX) {
                                    JOptionPane.showMessageDialog(null, "Há-Há você é muito ruim");
                                    System.exit(0);
                                }
                                if (esquerda && corpoX == cabecaX) {
                                    JOptionPane.showMessageDialog(null, "Há-Há você é muito ruim");
                                    System.exit(0);

                                }
                                if (cima && corpoY == cabecaY) {
                                    JOptionPane.showMessageDialog(null, "Há-Há você é muito ruim");
                                    System.exit(0);

                                }
                                if (baixo && corpoY == cabecaY) {
                                    JOptionPane.showMessageDialog(null, "Há-Há você é muito ruim");
                                    System.exit(0);

                                }
                            }
                        }
                    }
                    repaint();
                } else {
                    System.exit(0);
                }
            }
        }
    }

    private void inicio() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Snake");
        getContentPane().setBackground(Color.BLACK);
    }

    private boolean verificaMaca () {
        boolean retorno = false;
        if (cabecaY == macaY && cabecaX == macaX) {
            retorno = true;
        } else {retorno = false;}
        return retorno;
    }

    private void editarMaca () {
        retornaPosMaca();
        lMaca.setBounds(macaX, macaY, 20, 20);
        lMaca.setVisible(true);
        add(lMaca);
    }

    private void editarComponentes () {
        retornaPosMaca();
        retornaPosCabeca();
        lMaca.setBounds(macaX, macaY, 20, 20);
        lCabeca.setBounds(cabecaX, cabecaY, 20, 20);
        corpoX = cabecaX;
        corpoY = cabecaY;
        add(lMaca);
        add(lCabeca);
    }

    private void retornaPosMaca() {
        Random random = new Random();
        this.macaX = random.nextInt(674);
        int teste = this.macaX%20;
        this.macaX = this.macaX - teste;
        this.macaY = random.nextInt(451);
        teste = this.macaY%20;
        this.macaY = this.macaY - teste;
    }

    private void retornaPosCabeca() {
        Random random = new Random();
        this.cabecaX = random.nextInt(600);
        int teste = this.cabecaX%20;
        this.cabecaX = this.cabecaX - teste;
        this.cabecaY = random.nextInt(400);
        teste = this.cabecaY % 20;
        this.cabecaY = this.cabecaY - teste;
    }

    public static void main(String[] args) {
       Game game = new Game();
    }
}