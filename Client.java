/*
 * Programmer: Derek Jones
 * Date: 2019-07-25
 * Program Name: Client
 * Program Description: holds information of a client
 */
public class Client{
    //name of client
    String name;
    //credit card number of client
    String creditNumber;
    //phone number of client
    String phoneNumber;
    //number of days client is staying
    int daysStaying;
    //time in milliseconds that the client will leave
    long timeLeaving;
    //type of room client will have
    int roomType;
    //client's room number
    int roomNumber;
    //time in milliseconds that the client entered
    long timeEntered;

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: setRoomNumber
     * Program Descrption: sets the clients room number
     * Inputs: room number
     * Outputs: room number
     */
    public void setRoomNumber(int number){
        //set the room number of the client to the input room number
        roomNumber = number;
    }

}