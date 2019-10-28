package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        long timeEntered, exitTime, carID;
        File file = new File("input1.txt");
        Scanner sc = new Scanner(file);
        ParkingLotGroup groupA = new ParkingLotGroup();
        ParkingLot parkingLotA = groupA.parkingLot("standard");
        ParkingLot parkingLotB = groupA.parkingLot("premium");
        do{
            timeEntered = sc.nextLong();
            exitTime = sc.nextLong();
            carID = sc.nextLong();
            Car car = new Car(parkingLotB);
            parkingLotA.timeEntered = timeEntered;
            parkingLotB.timeEntered = timeEntered;

            parkingLotA.exitTime = exitTime;
            parkingLotB.exitTime = exitTime;

            parkingLotA.carID = carID;
            parkingLotB.carID = carID;

            parkingLotA.usedSpace++;
            parkingLotB.usedSpace++;

            if(parkingLotB.usedSpace <= com.company.ParkingLot.CAPACITY){
                String strCarID = String.format("Car %d", carID);
                String strTicketStamp = String.format("Timestamp: %s", car.receiveTicket());
                car.timeStamp = strTicketStamp;
                String strTicketPrice = String.format("Ticket price: %.2f", car.presentTicket());

                System.out.println(strCarID + "\t" + strTicketStamp + "\t" + strTicketPrice);
            } else{System.out.println("Sorry, Parking Lot is full...");}

        } while(sc.hasNext());

        groupA.setPolicy();
        System.out.println("\n" + groupA.getPolicy() + "\n");

        Car carAlpha = new Car();
        System.out.println("carAlpha is inquiring parking lot groupA prices...\n\n");
        carAlpha.getInquiry(groupA);
    }
}
class Car{

    double ticketPrice;
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    private ParkingLot parkingLot;

    public Car(){}

    public Car(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    String receiveTicket(){
        String receivedTicket = parkingLot.entranceGate();
        return receivedTicket;
    }

    double presentTicket(){
        double ticketPrice = parkingLot.getPrice();
        return ticketPrice;
    }

    void getInquiry(ParkingLotGroup group){
        System.out.println(group.getInquiry());
    }

    void chooseLowest(ParkingLot group){

    }
}

abstract class ParkingLot{
    static final int CAPACITY = 50;
    long timeEntered, exitTime, carID, allocatedTime;
    int usedSpace = 0;
    int openSpace = CAPACITY - usedSpace;
    double discount, ticketPrice;
    boolean left = false;

    static final int DECREMENT_USED_SPACE = -1;

    ParkingLotGroup group;

    double setPrice(){return ticketPrice;}

    double getPrice(){return this.setPrice();}

    String entranceGate(){
        String entrancePrompt = "Press button to receive ticket...";
        String ticketStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        return ticketStamp;
    }

    String exitGate(){
        String exitGate = "Pay Here, Credit Card/Debit";
        return exitGate;
    }

    String latestPrice(){
        StringBuffer latestPrice = new StringBuffer();
        return latestPrice.toString();
    }
}

class StandardParkingLot extends ParkingLot{
    public StandardParkingLot(){
        discount = .10;
    }

    double setPrice(){
        allocatedTime = exitTime - timeEntered;
        if(allocatedTime < 100){
            ticketPrice = 0.0;
        }
        else if(allocatedTime < 200){
            ticketPrice = 5.00;
        }
        else if(allocatedTime < 300){
            ticketPrice = 10.00;
        }
        else if(allocatedTime < 400){
            ticketPrice = 15.00;
        }
        else if(allocatedTime > 400){
            ticketPrice = 20.00;
        }
        return ticketPrice;
    }

    String latestPrice(){
        StringBuffer latestPrice = new StringBuffer();
        latestPrice.append("Standard Prices:\n" +
                "< 1 Hour: $0.00\n" +
                "< 2 Hours: $5.00\n" +
                "< 3 Hours: $10.00\n" +
                "< 4 Hours: $15.00\n" +
                "< 5 Hours: $20.00\n");

        return latestPrice.toString();
    }

}

class PremiumParkingLot extends ParkingLot{
    public PremiumParkingLot(){
        discount = 0.0;
    }
    double setPrice(){
        allocatedTime = exitTime - timeEntered;
        if(allocatedTime < 100){
            ticketPrice = 5.0;
        }
        else if(allocatedTime < 200){
            ticketPrice = 10.00;
        }
        else if(allocatedTime < 300){
            ticketPrice = 15.00;
        }
        else if(allocatedTime < 400){
            ticketPrice = 20.00;
        }
        else if(allocatedTime > 400){
            ticketPrice = 25.00;
        }
        return ticketPrice;
    }

    String latestPrice(){
        StringBuffer latestPrice = new StringBuffer();
        latestPrice.append("Premium Prices:\n" +
                "< 1 Hour: $5.00\n" +
                "< 2 Hours: $10.00\n" +
                "< 3 Hours: $15.00\n" +
                "< 4 Hours: $20.00\n" +
                "< 5 Hours: $25.00\n");

        return latestPrice.toString();
    }
}

class ParkingLotGroup{
    ParkingLot parkingLot = null;
    StringBuffer policy = new StringBuffer();

    public ParkingLot parkingLot(String type){
        if (type.equals("standard")){
            parkingLot = new StandardParkingLot();
        }else if(type.equals("premium")){
            parkingLot = new PremiumParkingLot();
        }

        return parkingLot;
    }

    void setPolicy(){
        this.policy.append("Company Policy:\n\n" +
                "Lorem ipsum dolor sit amet, habemus definitiones ut eos, dicit albucius ea cum, sonet tamquam gloriatur quo ut. Per an dolorum platonem. " +
                "Erat prima inermis qui cu. Dicat dicant aliquam in est, porro volumus vim ex. Sea justo theophrastus in.")
                .append("\n")
                .append("Sea id nostrud accusam temporibus, integre intellegat consectetuer te nam. Cum harum clita accommodare in, ne illum congue euripidis eam, usu " +
                        "maiorum eleifend salutatus ad. His possim honestatis an, nobis melius maiorum et eum, ad sit tempor gubergren.\n Eu facete delicatissimi pri, ne" +
                        " legere accusamus expetendis pri. Illud ignota quaeque id per, suas tota solet ne sit, error fastidii salutandi quo eu.")
                .append("\n")
                .append("Mea an tantas essent possim. Et est veri commodo consetetur, cu quo velit oportere, cum ei illum nominavi accusata. Qui eu idque senserit. Cibo" +
                        " sale in pro, incorrupte disputando ei sed, dicunt percipit hendrerit per ad.");
    }

    String getPolicy(){
        return this.policy.toString();
    }

    String getInquiry(){
        StringBuffer inquiry = new StringBuffer();
        inquiry.append(parkingLot("standard").latestPrice() + "\n");
        inquiry.append(parkingLot("premium").latestPrice());

        return inquiry.toString();
    }
}