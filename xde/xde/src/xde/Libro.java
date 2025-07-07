
package xde;

public class Libro {
    private String titulo;
    private String autor;
    private int año;
    private String genero; // Nuevo campo para género

    public Libro(String titulo, String autor, int año, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAño() {
        return año;
    }

    public String getGenero() {
        return genero;
    }
}