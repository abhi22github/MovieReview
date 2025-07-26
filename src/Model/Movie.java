package Model;

public class Movie {
    private String title;
    private Language language;
    private Genre genre;
    private String description;
    private String imagePath;
    private  String clipUrl;

    public String getClipUrl() {
        return clipUrl;
    }

    public void setClipUrl(String clipUrl) {
        this.clipUrl = clipUrl;
    }

    public Movie(String title, Language language, Genre genre, String description, String imagePath, String clipUrl) {
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.description = description;
        this.imagePath = imagePath;
        this.clipUrl = clipUrl;
    }

    public String getTitle() { return title; }
    public Language getLanguage() { return language; }
    public Genre getGenre() { return genre; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return title + " (" + language + ", " + genre + ")";
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
