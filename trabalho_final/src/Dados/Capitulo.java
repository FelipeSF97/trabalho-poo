package Dados;
public class Capitulo {
    private int numeroCap; 
    private String titulo;
    private int numPaginas;
    private Obra obra;

    public Capitulo(int numeroCap, String titulo, int numPaginas, Obra obra) {
        this.titulo = titulo;
        this.numeroCap = numeroCap;
        this.numPaginas = numPaginas;
        this.obra = obra;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroCap(){
        return numeroCap;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public Obra getObra() {
        return obra;
    }

}