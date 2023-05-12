package server.utils;

import common.data.Car;
import common.data.Coordinates;
import common.data.HumanBeing;
import common.data.Mood;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import server.managers.CollectionManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class XmlUtil {

    public static boolean saveXmlFile(Environment environment) throws WrongScriptException {
        OutputStream fileOutputStream;

        try {
            fileOutputStream = new FileOutputStream(environment.getBufferedReader().readLine());
            JAXBContext jaxbContext = JAXBContext.newInstance(environment.getCollectionManager().getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(environment.getCollectionManager(),fileOutputStream);
            System.out.println("File is saved");
            return true;
        } catch (IOException e){
            if(environment.getPointer()>0){
                throw new WrongScriptException();
            }
            environment.getPrintStream().println("File is not found or no property, check it again please");
            return false;
        } catch (PropertyException e) {
            if(environment.getPointer()>0){
                throw new WrongScriptException();
            }
            environment.getPrintStream().println("No property!");
            return false;
        } catch (JAXBException e) {
            environment.getPrintStream().println("JAXB error. Incorrect XML-document format or problems in the process of parsing and creating XML-document");
            return false;
        }
    }

    public static CollectionManager XMLParser(String link) throws ParserConfigurationException, IOException, SAXException {
        class Box{
            public long variable;
            public Box(long variable){
                this.variable = variable;
            }
        }
        Box box = new Box(0);
        CollectionManager collectionManager = new CollectionManager();
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(link));
        doc.getDocumentElement().normalize();
        NodeList all = doc.getElementsByTagName("entry");
        for (int count = 0; count < all.getLength(); count++) {
            Node first = doc.getElementsByTagName("entry").item(count);
            HumanBeing humanBeing = new HumanBeing();
            Car car = new Car();
            NodeList nodeList = first.getChildNodes();
            int n = nodeList.getLength();
            Node current;
            for (int i = 0; i < n; i++) {

                Coordinates coordinates = new Coordinates(0f, 0);
                current = nodeList.item(i);
                if (current.getNodeType() == Node.ELEMENT_NODE) {
                    switch (current.getNodeName()) {
                        case "key" -> box.variable = Long.parseLong(current.getTextContent());
                        case "value" -> {
                            NodeList inner = current.getChildNodes();
                            Node parameters;
                            for (int j = 0; j < inner.getLength(); j++) {
                                parameters = inner.item(j);
                                if (parameters.getNodeType() == Node.ELEMENT_NODE) {
                                    switch (parameters.getNodeName()) {
                                        case "name" -> {
                                            String name = parameters.getTextContent();
                                            humanBeing.setName(name);
                                        }
                                        case "id" -> {
                                            Long id = Long.parseLong(parameters.getTextContent());
                                            humanBeing.setId(id);
                                        }
                                        case "mood" -> {
                                            Mood mood = Mood.valueOf(parameters.getTextContent());
                                            humanBeing.setMood(mood);
                                        }
                                        case "realHero" -> {
                                            boolean flag = Boolean.parseBoolean(parameters.getTextContent());
                                            humanBeing.setRealHero(flag);
                                        }
                                        case "coordinates" -> {
                                            NodeList coordinateChildren = parameters.getChildNodes();
                                            for (int t = 0; t < coordinateChildren.getLength(); t++) {
                                                Node variable = coordinateChildren.item(t);
                                                if (variable.getNodeType() == Node.ELEMENT_NODE) {
                                                    switch (variable.getNodeName()) {
                                                        case "x" -> {
                                                            String xLine = variable.getTextContent();
                                                            float x = Float.parseFloat(xLine.replace(",", "."));
                                                            coordinates.setX(x);
                                                        }
                                                        case "y" -> {
                                                            int y = Integer.parseInt(variable.getTextContent());
                                                            coordinates.setY(y);
                                                        }
                                                        default -> {}
                                                    }
                                                }
                                            }
                                        }
                                        case "impactSpeed" -> {
                                            float speed = Float.parseFloat(parameters.getTextContent());
                                            humanBeing.setImpactSpeed(speed);
                                        }
                                        case "soundtrackName" ->
                                                humanBeing.setSoundtrackName(parameters.getTextContent());
                                        case "hasToothpick" ->
                                                humanBeing.setHasToothpick(Boolean.parseBoolean(parameters.getTextContent()));
                                        case "minutesOfWaiting" ->
                                                humanBeing.setMinutesOfWaiting(Float.parseFloat(parameters.getTextContent()));
                                        case "creationDate" ->
                                                humanBeing.setCreationDate(LocalDate.parse(parameters.getTextContent(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                        case "car" -> {
                                            NodeList carChildren = parameters.getChildNodes();
                                            for (int t = 0; t < carChildren.getLength(); t++) {
                                                Node variable = carChildren.item(t);
                                                if (variable.getNodeType() == Node.ELEMENT_NODE) {
                                                    switch (variable.getNodeName()) {
                                                        case "cool" -> {
                                                            boolean cool = Boolean.parseBoolean(variable.getTextContent());
                                                            car.setCool(cool);
                                                        }
                                                        case "name" -> car.setName(variable.getTextContent());
                                                        default -> {}
                                                    }
                                                }
                                            }
                                        }
                                        default -> {}
                                    }
                                }
                            }
                            humanBeing.setCoordinates(coordinates);
                            humanBeing.setCar(car);
                            collectionManager.getPeople().put(box.variable, humanBeing);
                        }
                    }
                }
            }
        }
        return collectionManager;
    }
}