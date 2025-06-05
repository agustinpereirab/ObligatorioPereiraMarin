package um.edu.uy.Entities;

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

    public boolean isBelongsToCollection() {
        return belongsToCollection;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getBudget() {
        return budget;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }
}
