package um.edu.uy.Entities;


import lombok.Data;

@Data
public class Movies {

    private boolean belongsToCollection;
    private int revenue;
    private int budget;
    private String[] genres;
    private int id;
    private String originalLanguage;
    private String title;

    public Movies(boolean belongsToCollection, int revenue, int budget, String[] genres, int id, String originalLanguage, String title) {
        this.belongsToCollection = belongsToCollection;
        this.revenue = revenue;
        this.budget = budget;
        this.genres = genres;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.title = title;
    }


}
