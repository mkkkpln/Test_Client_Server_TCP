package common.data;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jetbrains.annotations.NotNull;
import server.utils.DateAdapter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
@XmlType(name = "HumanBeing")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HumanBeing {
    @NotNull
    @XmlElement(name = "id", required=true)
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    @XmlElement(name = "name", required=true)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    @XmlElement(name = "coordinates", required=true)
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    @XmlElement(name = "creationDate", required=true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    @XmlElement(name = "realHero", required=true)
    private Boolean realHero; //Поле не может быть null
    @NotNull
    @XmlElement(name = "hasToothpick", required=true)
    private boolean hasToothpick;
    @NotNull
    @XmlElement(name = "impactSpeed", required=true)
    private float impactSpeed; //Максимальное значение поля: 491
    @NotNull
    @XmlElement(name = "soundtrackName", required=true)
    private String soundtrackName; //Поле не может быть null
    @NotNull
    @XmlElement(name = "minutesOfWaiting", required=true)
    private float minutesOfWaiting;
    @NotNull
    @XmlElement(name = "mood", required=true)
    private Mood mood; //Поле не может быть null
    @NotNull
    @XmlElement(name = "car", required=true)
    private Car car; //Поле может быть null

    public HumanBeing(){}

    public HumanBeing(Long id, String name, Coordinates coordinates, Boolean realHero, boolean hasToothpick, float impactSpeed, String soundtrackName, float minutesOfWaiting, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.mood = mood;
        this.car = car;
    }

    public HumanBeing(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        try {
            if (id==null || id <= 0){
                throw new IOException("You can not put NULL and lower 0 in id.");
            }
            else {
                this.id = id;
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            if (name==null || name.isEmpty()){
                throw new IOException("You can not put NULL in name.");
            }
            else {
                this.name=name;
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        try {
            if (coordinates==null){
                throw new IOException("You can not put NULL in coordinates.");
            }
            else {
                this.coordinates=coordinates;
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public float getImpactSpeed() {

        return impactSpeed;
    }

    public void setImpactSpeed(float impactSpeed) {
        try{
            if (impactSpeed > 491) {
                throw new IOException("Maximum field value: 491");
            }
            else {
                this.impactSpeed = impactSpeed;;
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public void setSoundtrackName(String soundtrackName) {
        try {
            if (soundtrackName==null){
                throw new IOException("You can not put NULL in SoundTrack name.");
            }
            else {
                this.soundtrackName=soundtrackName;
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public float getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public void setMinutesOfWaiting(float minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        try {
            if (mood==null){
                throw new IOException("You can not put null in MOOD.");
            }
            else {
                this.mood=mood;
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        try {
            if (car==null){
                throw new IOException("You can not put null in CAR.");
            }
            else {
                this.car=car;
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return hasToothpick == that.hasToothpick && Float.compare(that.impactSpeed, impactSpeed) == 0 && Float.compare(that.minutesOfWaiting, minutesOfWaiting) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(realHero, that.realHero) && Objects.equals(soundtrackName, that.soundtrackName) && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("ID : %d",id)).append("\n");
        builder.append(String.format("Name : " + name)).append("\n");
        builder.append(String.format("Creation Date : " + creationDate.toString())).append("\n");
        builder.append(String.format("Speed : %.3f", impactSpeed)).append("\n");
        builder.append(String.format("Name of the soundtrack : " + soundtrackName)).append("\n");
        builder.append(String.format("Minutes of waiting : %.3f", minutesOfWaiting)).append("\n");
        builder.append(String.format("Машина : " + car.toString())).append("\n");
        builder.append(String.format("Настроение : " + mood)).append("\n");

        return builder.toString();
    }

    public int compareTo(HumanBeing o) {
        return (int) (this.id - o.getId());
    }
}