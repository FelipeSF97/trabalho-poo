package Dados;

public class Manhwa extends Obra {

    private int capitulos;

    public Manhwa(String titulo, String autor, int ano, int capitulos) {
        super(titulo, autor, ano);
        this.capitulos = capitulos;
    }


    public int getCapitulos() {
        return capitulos;
    }

    @Override
    public int getNumeroUnidades() {
        return capitulos;
    }

    @Override
    public String getTipo() {
        return "Manhwa";
    }
}
