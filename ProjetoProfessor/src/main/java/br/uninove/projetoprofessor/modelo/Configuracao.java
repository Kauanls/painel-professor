package br.uninove.projetoprofessor.modelo;

public class Configuracao {
    private int id;
    private int limiteFaltas;

    public Configuracao() {}

    public Configuracao(int id, int limiteFaltas) {
        this.id = id;
        this.limiteFaltas = limiteFaltas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLimiteFaltas() { return limiteFaltas; }
    public void setLimiteFaltas(int limiteFaltas) { this.limiteFaltas = limiteFaltas; }
}