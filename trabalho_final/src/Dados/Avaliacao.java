package Dados;

public class Avaliacao {

    private final Usuario usuario;
    private final Obra obra;
    private int nota;            
    private String comentario;   

    public Avaliacao(Usuario usuario, Obra obra, int nota, String comentario) {
        if (usuario == null || obra == null)
            throw new IllegalArgumentException("Usuário e obra não podem ser null.");

        if (nota < 1 || nota > 10)
            throw new IllegalArgumentException("A nota deve ser entre 1 e 10.");

        this.usuario = usuario;
        this.obra = obra;
        this.nota = nota;
        this.comentario = comentario == null ? "" : comentario;
    }

     public Usuario getUsuario() {
        return usuario;
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

    @Override
    public String toString() {
        return "Nota: " + nota + " - " + comentario;
    }
}
