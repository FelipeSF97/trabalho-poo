package Dados;

public class Avaliacao {

    private int nota;            // 1 a 10
    private String comentario;   // opcional

    public Avaliacao(int nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    @Override
    public String toString() {
        return "Nota: " + nota + " - " + comentario;
    }
}
