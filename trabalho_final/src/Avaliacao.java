public class Avaliacao {

    private Obra obra;
    private int nota;
    private String comentario;

    public Avaliacao(Obra obra, int nota, String comentario) {
        this.obra = obra;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Obra getObra() {
        return obra;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}

