package um.edu.uy.Entities;


import lombok.Data;

@Data
public class Actors {
    private String name;
    private int amountOfCalifications;
    private int amountOfMovies;

    public  Actors(String name, int amountOfCalifications, int amountOfMovies) {
        this.name = name;
        this.amountOfCalifications = amountOfCalifications;
        this.amountOfMovies = amountOfMovies;
    }
}
