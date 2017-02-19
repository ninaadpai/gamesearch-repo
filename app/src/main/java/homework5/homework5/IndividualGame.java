package homework5.homework5;

import java.io.Serializable;

/**
 * Created by ninaadpai on 2/18/17.
 */

public class IndividualGame implements Serializable{
    String name,overview,genre,publisher,videoUrl;

    public IndividualGame() {

    }

    @Override
    public String toString() {
        return "IndividualGame{" +
                "name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

    public IndividualGame(String name, String videoUrl, String overview, String genre, String publisher) {
        this.name = name;
        this.videoUrl = videoUrl;
        this.overview = overview;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
