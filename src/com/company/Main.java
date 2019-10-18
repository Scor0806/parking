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
        ParkingLot ParkingLot = new ParkingLot();
        do{
            timeEntered = sc.nextLong();
            exitTime = sc.nextLong();
            carID = sc.nextLong();
            Car car = new Car();
            car.timeEntered = timeEntered;
            car.exitTime = exitTime;
            car.carID = carID;
            ParkingLot.usedSpace++;
            if(ParkingLot.usedSpace <= com.company.ParkingLot.CAPACITY){
                String strCarID = String.format("Car %d", carID);
                String strTicketStamp = String.format("Timestamp: %s", car.timeStamp);
                String strTicketPrice = String.format("Ticket price: %.2f", car.getPrice());

                System.out.println(strCarID + "\t" + strTicketStamp + "\t" + strTicketPrice);
            } else{System.out.println("Sorry, Parking Lot is full...");}

        } while(sc.hasNext());
    }
}
class Car{
    long timeEntered, exitTime, carID, allocatedTime;
    double ticketPrice;
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    Car(){

    }
    double getPrice(){
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
}

class ParkingLot{
    static final int CAPACITY = 50;
    int usedSpace = 0;
    static final int DECREMENT_USED_SPACE = -1;

    ParkingLot(){

    }

}