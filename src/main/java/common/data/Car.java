package common.data;

public class Car {
    private String name; //Can be null
    private Boolean cool; //Can be null

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCool() {
        return cool;
    }

    public void setCool(Boolean cool) {
        this.cool = cool;
    }

    @Override
    public String toString() {
        return name + "\t" + "\ncool : " + cool.toString();
    }
}