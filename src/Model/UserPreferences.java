package Model;

public class UserPreferences {
    private String name;
    private Language language;
    private Genre genre;



    public UserPreferences() {
    }

    public UserPreferences(Genre genre, String name, Language language) {
        this.genre = genre;
        this.name = name;
        this.language = language;
    }


    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
