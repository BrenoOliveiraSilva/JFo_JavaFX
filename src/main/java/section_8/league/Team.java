package section_8.league;

public class Team {
    private final String name;
    private int wins;
    private int losses;
    private int ties;
    private int pointsScored;
    private int pointsAllowed;

    public Team(String name) {
        this.name = name;
    }

    public String getTeamResults() {
        return  name +
                "\nVitórias: " + wins + ", Derrotas: " + losses + ", Empates: " + ties +
                "\nGols marcados: " + pointsScored + ", Gols sofridos: " + pointsAllowed;
    }
    public String getName() {
        return name;
    }

    public void setPointsAllowed(int pointsAllowed) {
        this.pointsAllowed += pointsAllowed;
    }
    public void setPointsScored(int pointsScored) {
        this.pointsScored += pointsScored;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public void setLosses(int losses) {
        this.losses += losses;
    }

    public void setTies(int ties) {
        this.ties += ties;
    }
}