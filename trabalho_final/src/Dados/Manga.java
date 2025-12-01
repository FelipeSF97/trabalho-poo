package Dados;

public class Manga extends Obra {

    private int volumes;

    public Manga(String titulo, String autor, int ano, int volumes) {
        super(titulo, autor, ano);
        this.volumes = volumes;
    }

    public int getVolumes() {
        return volumes;
    }

    @Override
    public int getNumeroUnidades() {
        return volumes;
    }

    @Override
    public String getTipo() {
        return "Manga";
    }
}
