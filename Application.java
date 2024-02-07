import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 800, 768);
    }
    ArrayList<passengerinfo> passengers = new ArrayList<passengerinfo>();
    public void run() {
        //Start here! Good luck with the test!

        boolean menu = true;
        while (menu) {


            SaxionApp.printLine("Welcome to the brittania's passenger list! ");
            SaxionApp.printLine("--------------------------");
            SaxionApp.printLine("Number of passengers: " + passengers.size());
            SaxionApp.printLine("1. read from passengers.csv");
            SaxionApp.printLine("2. show all passengers");
            SaxionApp.printLine("3. add a passenger");
            SaxionApp.printLine("4. show statistics");
            SaxionApp.printLine("0. exit");
            char select = SaxionApp.readChar();

            if (select == '1') {
                readcsv();
            }
            else if (select == '2') {
                showpassengers();

            }
            else if (select == '3') {
                addpassenger();

            }
            else if (select == '4') {

                SaxionApp.clear();
                SaxionApp.setFill(Color.GREEN);
                SaxionApp.drawBorderedText("the passenger list", 0,0,32);
                SaxionApp.drawBorderedText("the oldest passenger is: "+ oldest(),0,40,20);
                SaxionApp.drawBorderedText("the total wheight of all passengers is " + totalwheight(),0,60,20);
                SaxionApp.drawBorderedText("percentage of passngers per class",0,90,20);
                SaxionApp.drawBorderedText("class 1", 0,130,15);
                SaxionApp.drawBorderedText("class 2", 0,160,15);
                SaxionApp.drawBorderedText("class 3", 0,190,15);
                SaxionApp.setFill(Color.darkGray);
                SaxionApp.drawRectangle(200,130,500,20);
                SaxionApp.drawRectangle(200,160,500,20);
                SaxionApp.drawRectangle(200,190,500,20);
                SaxionApp.setFill(Color.YELLOW);
                SaxionApp.drawRectangle(200,130,firstclass(),20);
                SaxionApp.drawRectangle(200,160,secondclass(),20);
                SaxionApp.drawRectangle(200,190,thirdclass(),20);






            }
            else if (select == '0') {
                menu = false;

            }

            SaxionApp.pause();
            SaxionApp.clear();

        }





    }
    void readcsv() {
        CsvReader reader = new CsvReader("C:\\programeer opdrachten\\britania(1)\\britania\\passengers.csv");
        reader.setSeparator(',');
        reader.skipRow();
        while (reader.loadRow()) {
            String name = reader.getString(0);
            String sex = reader.getString(1);
            int clas = reader.getInt(2);
            double wheight = reader.getDouble(3);
            int age = reader.getInt(4);

            passengerinfo passenger = new passengerinfo();
            passenger.pname = name;
            passenger.psex = sex;
            passenger.pclass = clas;
            passenger.pwheight = wheight;
            passenger.page = age;

            passengers.add(passenger);
        }
    }
    void showpassengers(){
        for (passengerinfo data : passengers) {
            SaxionApp.printLine(data.pname+" "+data.psex+ " "+data.pclass+ " "+data.pwheight+" "+data.page);


        }

    }
    void addpassenger(){
        passengerinfo passenger = new passengerinfo();
        SaxionApp.print("add name: ");
        passenger.pname = SaxionApp.readString();
        SaxionApp.print("add sex m or f: ");
        passenger.psex = SaxionApp.readString();
        while (!passenger.psex.equals("m")  && !passenger.psex.equals("f")){
            SaxionApp.printLine("incorrect sex", Color.red);
            SaxionApp.print("add sex m or f: ");
            passenger.psex = SaxionApp.readString();


        }

        SaxionApp.print("add class: ");
        passenger.pclass = SaxionApp.readInt();
        while (passenger.pclass != 3 && passenger.pclass != 2 && passenger.pclass != 1){
            SaxionApp.printLine("incorrect class", Color.red);
            SaxionApp.print("add class: ");
            passenger.pclass = SaxionApp.readInt();

        }
        SaxionApp.print("add wheight: ");
        passenger.pwheight = SaxionApp.readDouble();
        SaxionApp.print("add age: ");
        passenger.page = SaxionApp.readInt();
        while (passenger.page>110){
            SaxionApp.printLine("too old!",Color.red);
            SaxionApp.print("add age: ");
            passenger.page = SaxionApp.readInt();

        }

        passengers.add(passenger);




    }
    public String oldest(){
        String oldestname = "";
        int age = 0;
        for (passengerinfo oldest: passengers){
            if (age<oldest.page){
                age = oldest.page;
                oldestname = oldest.pname;
            }

        }
        return(oldestname);
    }
    public double totalwheight(){
        double sum = 0;
        for (passengerinfo total: passengers)
            sum = sum + total.pwheight;

        return(sum);
    }
    public int firstclass(){
        int counter = 0;
        for (passengerinfo first: passengers)
            if (first.pclass == 1){
                counter++;
            }
        double totaal = passengers.size();
       int width = (int)((counter/totaal)*100)*5;
        return(width);
    }
    public int secondclass(){
        int counter = 0;
        for (passengerinfo first: passengers)
            if (first.pclass == 2){
                counter++;
            }
        double totaal = passengers.size();
        int width = (int)((counter/totaal)*100)*5;
        return(width);

    }
    public int thirdclass(){
        int counter = 0;
        for (passengerinfo first: passengers)
            if (first.pclass == 3){
                counter++;
            }
        double totaal = passengers.size();
        int width = (int)((counter/totaal)*100)*5;
        return(width);

    }
}







