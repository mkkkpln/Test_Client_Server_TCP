package common.data;

import jakarta.xml.bind.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
@XmlType(name = "Coordinates")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @NotNull
    @XmlElement(name = "x", required=true)
    private Float x; //The field value must be greater than -598, can not be null
    @NotNull
    @XmlElement(name = "y", required=true)
    private Integer y; //The field value must be greater than -67, can not be null

    public Coordinates(){}
    public Coordinates(Float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        try{
            if (x==null || x <= -598) {
                System.out.println("Incorrect input!");
                throw new IOException("You can not enter null or less than -598");
            }
            else {
                this.x = x;
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        try{
            if (y == null || y <= -67) {
                System.out.println("Incorrect input!");
                throw new IOException("You can not enter null or less than -67");
            }
            else {
                this.y = y;
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}
