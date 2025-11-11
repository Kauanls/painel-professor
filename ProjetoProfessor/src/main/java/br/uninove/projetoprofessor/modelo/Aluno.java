package br.uninove.projetoprofessor.modelo;

public class Aluno {
    private int id;
    private String nome;
    private int numeroChamada;
    private int faltas;

    // Construtores
    public Aluno() {}

    public Aluno(int id, String nome, int numeroChamada, int faltas) {
        this.id = id;
        this.nome = nome;
        this.numeroChamada = numeroChamada;
        this.faltas = faltas;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getNumeroChamada() { return numeroChamada; }
    public void setNumeroChamada(int numeroChamada) { this.numeroChamada = numeroChamada; }

    public int getFaltas() { return faltas; }
    public void setFaltas(int faltas) { this.faltas = faltas; }
}