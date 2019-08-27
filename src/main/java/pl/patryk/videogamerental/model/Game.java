package pl.patryk.videogamerental.model;

import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 10000)
    private String description;
    private String developer;
    private String publisher;
    private String gameLanguage;
    private String subtitleLanguage;
    private String rating;

    @Lob
    @Column(length = 100000)
    private byte[] cover;

    @OneToMany(mappedBy = "game")
    private Set<Copy> copies;

    @OneToMany(mappedBy = "game")
    private Set<ReservationHistory> reservationHistories;

    public Game() {
    }

    public Game(String name, String description, String developer, String publisher, String gameLanguage, String subtitleLanguage, String rating, byte[] cover) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.gameLanguage = gameLanguage;
        this.subtitleLanguage = subtitleLanguage;
        this.rating = rating;
        this.cover = cover;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public String generateBase64Image() {
        return Base64.getEncoder().encodeToString(cover);
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }
}
