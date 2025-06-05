package um.edu.uy.Entities;

import lombok.Data;

@Data
public class Ratings {

    private int userId;
    private int movieId;
    private int rating;
    private long timestamp;

    public Ratings(int userId, int movieId, int rating, long timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}



