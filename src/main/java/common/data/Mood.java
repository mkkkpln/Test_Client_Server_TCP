package common.data;

public enum Mood {
    SORROW ("Sorrow"),
    GLOOM ("Gloom"),
    APATHY ("Apathy"),
    CALM ("Calm");

    private String mean;
    Mood(String name) {
        this.mean = name;
    }

    @Override
    public String toString() {
        return mean;
    }

}