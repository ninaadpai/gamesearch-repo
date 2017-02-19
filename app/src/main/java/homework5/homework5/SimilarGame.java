package homework5.homework5;

/**
 * Created by ninaadpai on 2/19/17.
 */

public class SimilarGame {
    String id , platformId;

    public SimilarGame() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SimilarGame{" +
                "id='" + id + '\'' +
                ", platformId='" + platformId + '\'' +
                '}';
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public SimilarGame(String id, String platformId) {
        this.id = id;
        this.platformId = platformId;
    }
}
