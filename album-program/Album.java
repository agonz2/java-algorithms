public class Album {
    // Album attributes
    private String title, performer, songTotal, genre;

    // Default constructor
    public Album(){}

    // Constructor with attributes
    public Album(String title, String performer, int genre, int songTotal){
        this.title = title;
        this.performer = performer;
        this.genre = setGenre(genre);
        this.songTotal = setTotal(songTotal);
    }

    // Setter methods of the attribute variables
    private void setTitle(String title){
        this.title = title;
    }

    private void setPerformer(String performer){
        this.performer = performer;
    }

    private String setGenre(int genre){
        switch (genre) {
            case 1 -> this.genre = "Hip-Hop";
            case 3 -> this.genre = "Orchestral";
            case 4 -> this.genre = "Your Parents";
            case 5 -> this.genre = "Theatre";
            default -> this.genre = "Easy Listening";
        }
        return this.genre;
    }

    private String setTotal(int songTotal){
        this.songTotal = toString(songTotal);
        return this.songTotal;
    }

    // Getter methods for the attribute variables
    public String getTitle(){
        return title;
    }

    public String getPerformer(){
        return performer;
    }

    public String getGenre(){
        return genre;
    }

    public String getTotal(){
        return songTotal;
    }

    // Assistance methods
    private String toString(int value){
        String string;
        string = Integer.toString(value);
        return string;
    }

    public boolean isLong(int total){
        boolean isLong;
        isLong = total >= 10;
        return isLong;
    }
}// end of Album class
