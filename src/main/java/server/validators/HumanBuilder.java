package server.validators;

import common.data.Car;
import common.data.HumanBeing;
import common.data.Mood;
import server.utils.*;
import utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HumanBuilder {

    public HumanBeing buildHuman(Environment environment) throws WrongScriptException, BuilderException {
        HumanBeing newHuman = new HumanBeing();


        Long id = (long) (environment.getCollectionManager().getPeople().size()+1);
        newHuman.setId(id);


        environment.getPrintStream().println("Enter the name");
        String name = "";

        for (int i = 0; i < 3; i++){
            try {
                name = environment.getBufferedReader().readLine();
                if(name == null || name.isEmpty() || name.split(" ").length == 0){
                    throw new BuilderException();
                }
                name = EditUtil.nameParser(name);
                break;

            } catch (IOException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().printf("Invalid input. You have %d attempts\n", 2-i);
            } catch (WrongNameException e) {
                environment.getPrintStream().println("Use only A-Z or a-z");
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
            }
        }


        newHuman.setName(name);


        environment.getPrintStream().println("Is he a hero? type: \'yes\' or \'no\'");
        boolean isHero = false;

        for (int i = 0; i < 3; i++) {
            try {
                isHero = EditUtil.boolParser(environment.getBufferedReader().readLine());
                break;
            } catch (WrongHeroException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("The answer is 'yes' or 'no' ");
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            } catch (IOException exception) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().println("Command finished!!!");
                throw new BuilderException();
            }
        }

        newHuman.setRealHero(isHero);

        environment.getPrintStream().println("Set Mood");
        System.out.println(List.of(Mood.values()));

        Mood mood = Mood.CALM;

        try {
            mood = Validator.moodParser(environment);
        } catch (WrongArgumentException e) {
            throw new BuilderException();
        }


        newHuman.setMood(mood);


        Float x= 0f;
        Integer y = 0;
        BufferedReader in = environment.getBufferedReader();
        for (int i = 0; i < 3; i++) {
            try {
                environment.getPrintStream().println("Enter Float x ");
                x = Validator.floatParser(environment);
                break;
            } catch (Exception e){
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }

                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                System.out.println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }
        for (int i = 0; i < 3; i++) {
            try {
                environment.getPrintStream().println("Enter Integer y");
                String line = in.readLine();
                line.replace('.',',');
                Scanner tmp = new Scanner(line);
                y = tmp.nextInt();
                if (line.split(" ").length > 1) {
                    environment.getPrintStream().println("Try again!");
                    throw new IOException();
                }
                break;
            } catch (Exception e){
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }



        newHuman.setCreationDate(LocalDate.now());



        boolean hasToothPick = false;
        environment.getPrintStream().println("Does he have a toothpick? type: \'yes\' or \'no\'");

        for (int i = 0; i < 3; i++) {
            try {
                hasToothPick = EditUtil.boolParser(environment.getBufferedReader().readLine());
                break;
            } catch (WrongHeroException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("The answer is 'yes' or 'no' ");
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            } catch (IOException exception) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("Invalid input!");
                environment.getPrintStream().println("Command finished!");
                throw new BuilderException();
            }
        }

        newHuman.setHasToothpick(hasToothPick);


        Float speed = 0f;
        for (int i = 0; i < 3; i++) {
            try {
                environment.getPrintStream().println("Enter the speed (Float type)");
                speed = Validator.floatParser(environment);
                break;
            }catch (Exception e){
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                System.out.println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }

        newHuman.setImpactSpeed(speed);


        Float time;
        environment.getPrintStream().println("Enter minutes of waiting (Float type)");

        for (int i = 0; i < 3; i++) {
            try {
                time = Validator.floatParser(environment);
                newHuman.setMinutesOfWaiting(time);
                break;
            }catch (Exception e){
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                System.out.println("Invalid input");
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            }
        }



        environment.getPrintStream().println("Enter the soundtrack name");
        String musicName = "";

        for (int i = 0; i < 3; i++){
            try {
                musicName = environment.getBufferedReader().readLine();
                if(musicName == null || musicName.isEmpty() || musicName.split(" ").length == 0){
                    throw new BuilderException();
                }
                break;

            } catch (IOException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();

                }
                environment.getPrintStream().printf("Invalid input. You have %d attempts\n", 2-i);
            }
        }

        newHuman.setSoundtrackName(musicName);


        environment.getPrintStream().println("Let's create a car!");


        Car car = new Car();
        boolean isCool = false;


        environment.getPrintStream().println("Is it Cool? Type 'yes' or 'no'");
        for (int i = 0; i < 3; i++) {
            try {
                isCool = EditUtil.boolParser(environment.getBufferedReader().readLine());
                break;
            } catch (WrongHeroException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("The answer is 'yes' or 'no' ");
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().printf("You have %d attempts\n", 2-i);
            } catch (IOException exception) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                environment.getPrintStream().println("Invalid input");
                environment.getPrintStream().println("Command finished!!!");
                throw new BuilderException();
            }
        }

        environment.getPrintStream().println("Type the name of your car!");
        String carName = "";

        for (int i = 0; i < 3; i++){
            try {
                carName = environment.getBufferedReader().readLine();
                if(carName == null || carName.isEmpty() || carName.split(" ").length == 0){
                    throw new IOException();
                }
                break;

            } catch (IOException e) {
                if(environment.getPointer()>0){
                    throw new WrongScriptException();
                }
                if(i==2){
                    environment.getPrintStream().println("Command failed!");
                    throw new BuilderException();
                }
                environment.getPrintStream().printf("Invalid input. You have %d attempts\n", 2-i);
            }
        }
        car.setName(carName);
        car.setCool(isCool);
        newHuman.setCar(car);
        environment.getPrintStream().println("Command finished!");
        return newHuman;
    }
}


