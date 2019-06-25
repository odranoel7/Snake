package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Node {
    ImageIcon corpo;
    private JLabel valor;
    private Node anterior;

    Node proximo;


    public Node () {
        corpo = new ImageIcon(getClass().getResource("corpo.png"));
        valor = new JLabel(corpo);
    }

    public Node(JLabel vlr) {
        this.valor = vlr;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }

    public JLabel getValor() {
        return this.valor;
    }
}