package SnakeGame;

public class Fila {
    Node inicio;
    int contador = 0;
    Node ultimo;
    Node anterior;

    public boolean enfileirar(Node node){
        boolean bol = false;
        if(inicio == null){
            inicio = node;
            ultimo = node;
            anterior = node;
            bol = true;
            contador++;
        }else{
            Node aux = inicio;
            while(aux.getProximo() != null){
                aux = aux.getProximo();
            }
            ultimo = node;
            aux.setProximo(ultimo);
            ultimo.setAnterior(aux);
            contador++;
            bol = true;
        }
        return bol;
    }

    public Node getInicio(){
        return this.inicio;
    }

    public int size(){
        return contador;
    }

}