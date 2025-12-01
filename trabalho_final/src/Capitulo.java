public class Capitulo {
    private String titulo;
    private int numPaginas;
    private Obra obra;

    public Capitulo(String titulo, int numPaginas, Obra obra) {
        this.titulo = titulo;
        this.numPaginas = numPaginas;
        this.obra = obra;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public Obra getObra() {
        return obra;
    }

}