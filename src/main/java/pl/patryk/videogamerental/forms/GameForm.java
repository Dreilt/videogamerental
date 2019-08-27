package pl.patryk.videogamerental.forms;

import org.springframework.web.multipart.MultipartFile;

public class GameForm {

    private String name;
    private String description;
    private String developer;
    private String publisher;
    private String gameLanguage;
    private String subtitleLanguage;
    private String rating;
    private MultipartFile cover;

    public GameForm() {
    }

    public GameForm(String name, String description, String developer, String publisher, String gameLanguage, String subtitleLanguage, String rating, MultipartFile cover) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.gameLanguage = gameLanguage;
        this.subtitleLanguage = subtitleLanguage;
        this.rating = rating;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGameLanguage() {
        return gameLanguage;
    }

    public void setGameLanguage(String gameLanguage) {
        this.gameLanguage = gameLanguage;
    }

    public String getSubtitleLanguage() {
        return subtitleLanguage;
    }

    public void setSubtitleLanguage(String subtitleLanguage) {
        this.subtitleLanguage = subtitleLanguage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }
}