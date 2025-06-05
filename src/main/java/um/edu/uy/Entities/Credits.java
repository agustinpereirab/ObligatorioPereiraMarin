package um.edu.uy.Entities;

import lombok.Data;

@Data
public class Credits {
    private int userId;
    private String[] cast;
    private String[] crew;

    public Credits(int userId, String[] cast, String[] crew) {
        this.userId = userId;
        this.cast = cast;
        this.crew = crew;
    }

}
