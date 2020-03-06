//import necessary packages
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Programmer: Derek Jones
 * Date: 2019-07-25
 * Program Name: HotelMain
 * Program Description: A hotel managing system for clients and rooms
 */
public class HotelMain extends JFrame implements ActionListener{
    //an arraylist of clients called clientList
    ArrayList<Client> clientList = new ArrayList<Client>();
    //an array of abstractrooms of size 300
    AbstractRoom[] rooms = new AbstractRoom[300];

    //an instance of the hotel miain class called window
    static HotelMain window = new HotelMain();

    //set counter to 0
    int counter = 0;
    //set roomtype to 0
    int roomType = 0;

    //row of a table
    int row;

    //initialize container
    Container contentPane;
    //initialize JPanel
    JPanel myJPanel;
    //initialize JButton
    JButton button;
    //initialize JTable
    JTable table;
    //initialize JLabel
    JLabel L1;

    //initialize 4 text fields, name, phonenumber, cardnumber, length
    JTextField name, phoneNumber, cardNumber, length;

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: TimedTask
     * Program Description: a timertask that runs update when the timer tells it to
     */
    class TimedTask extends TimerTask{
        /*
         * Programmer: Derek Jones
         * 2019-07-25
         * Program Name: run
         * Program Description: runs update when the timer tells it to
         * Outputs: runs update
         */
        public void run(){
            //run update
            update();
        }
    }

    public static void main(String[] args) {
        //makes new timer called timer
        Timer timer = new Timer();

        //schedule a new timed task that runs every 10 seconds
        timer.schedule(window.new TimedTask(), 0, 10000);

        //set title of window to Hotel Manager
        window.setTitle("Hotel Manager");

        //set the size of window to 1200 by 650
        window.setSize(1200,650);
        
        //make window visible
        window.setVisible(true);
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: HotelMain
     * Program Description: constructor for HotelMain, initializes menu bar and client/ room informatinos
     * Inputs: clientinfo and room info
     * Outputs: menubar, clientinfo, roominfo
     */
    public HotelMain() {
        //initialize a menubar bar
        JMenuBar bar;

        //initialize jmenu menu
        JMenu menu;

        //initialize jmenuitem item
        JMenuItem item;

        //set bar to a new JMenuBar
        bar = new JMenuBar();
        //set the JMenuBar to bar
        setJMenuBar(bar);

        //set menu to a new Menu called quit
        menu = new JMenu("Quit");
        //add menu to bar
        bar.add(menu);

        //set item to a new item called quit
        item = new JMenuItem("Quit");
        //add action listener to item
        item.addActionListener(this);
        //add item to menu
        menu.add(item);

        //set menu to a new Menu called clients
        menu = new JMenu("Clients");
        //add menu to bar
        bar.add(menu);

        //set item to a new item called add client
        item = new JMenuItem("Add Client");
        //add action listener to item
        item.addActionListener(this);
        //add item to menu
        menu.add(item);

        //set item to a new item called remove client
        item = new JMenuItem("Remove Client");
        //add action listener to item
        item.addActionListener(this);
        //add item to menu
        menu.add(item);

        //set item to a new item called see clients
        item = new JMenuItem("See Clients");
        //add action listener to item
        item.addActionListener(this);
        //add item to menu
        menu.add(item);

        //set item to a new item called find room number
        item = new JMenuItem("Find Room Number");
        //add action listener to item
        item.addActionListener(this);
        //add item to menu
        menu.add(item);

        //set menu to a new Menu called rooms
        menu = new JMenu("Rooms");
        //add menu to bar
        bar.add(menu);

        //set item to a new item called clean rooms
        item = new JMenuItem("Clean Rooms");
        //add action listener to item
        item.addActionListener(this);
        //add item to menu
        menu.add(item);

        //test the following code
        try{
            //run initializeRooms
            initializeRooms();
            //run saveRooms
            saveRooms();
            //run LoadClients
            loadClients();
            //run saveClients
            saveClients();
        //catch any IOException
        }catch(IOException ex){
            //print IOExcpetion occured
            System.out.println("IOException occured");
        }

    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: actionPerformed
     * Program Description: runs various things based on what action was performed
     * Inputs: event action
     * Outputs: varys
     */
    public void actionPerformed(ActionEvent e){
        //set event to the action command of e
        String event = e.getActionCommand();

        //switch based on event
        switch(event){
            //if event is quit
            case "Quit":
                //exit the system
                System.exit(0);
                //break out of the switch statement
                break;
            //if event is add clients
            case "Add Client":
                //run addclient
                addClient();
                //break out of the switch statement
                break;
            //if event is Enter information
            case "Enter Information":
                //run enterinformation
                enterInformation();
                //break out of the switch statement
                break;
            //if event is remove client
            case "Remove Client":
                //run removewindow
                removeWindow();
                //break out of the switch statement
                break;
            //if event is remove
            case "Remove":
                //run removeclient
                removeClient();
                //break out of the switch statement
                break;
            //if event is double room
            case "Double Room":
                //set room type to 0
                roomType = 0;
                //break out of the switch statement
                break;
            //if event is suite
            case "Suite":
                //set roomtype to 1
                roomType = 1;
                //break out of the switch statement
                break;
            //if event is see clients
            case "See Clients":
                //run view clients
                viewClients();
                //break out of the switch statement
                break;
            //if event is clean rooms
            case "Clean Rooms":
                //run cleanrooms
                cleanRooms();
                //break out of the switch statement
                break;
            //if event is clean
            case "Clean":
                //run clean
                clean();
                //break out of the switch statement
                break;
            //if event is find room number
            case "Find Room Number":
                //run searchclients
                searchClients();
                //break out of the switch statement
                break;
            //if event is search
            case "Search":
                //run clients
                search();
                //break out of the switch statement
                break;
            //if event is anything else something has gone wrong
            default:
                //tell user that something went wrong
                System.out.println("Something went wrong with the switch statement");
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: addClient
     * Program Description: shows the gui for adding a client, allows user to input client information
     * Inputs: client information
     * Outputs: prompts, client information
     */
    public void addClient(){
        //set contentpane to the content pane
        contentPane = getContentPane();
        //initialize new jpanel
        myJPanel = new JPanel();

        //set the layout to myjpanel to box layout
        myJPanel.setLayout(new BoxLayout(myJPanel, BoxLayout.PAGE_AXIS));

        //label text field
        L1 = new JLabel("Client name");
        //add label to jpanel
        myJPanel.add(L1);
        //make new text field
        name = new JTextField(20);
        //add text field to jpanel
        myJPanel.add(name);

        //label text field
        L1 = new JLabel("Client Phone Number");
        //add label to jpanel
        myJPanel.add(L1);
        //make new text field
        phoneNumber = new JTextField(20);
        //add text field to jpanel
        myJPanel.add(phoneNumber);

        //label text field
        L1 = new JLabel("Credit Card Number");
        //add label to jpanel
        myJPanel.add(L1);
        //make new text field
        cardNumber = new JTextField(20);
        //add text field to jpanel
        myJPanel.add(cardNumber);

        //label text field
        L1 = new JLabel("Days Staying");
        //add label to jpanel
        myJPanel.add(L1);
        //make new text field
        length = new JTextField(2);
        //add text field to jpanel
        myJPanel.add(length);

        //make new jradiobutton called double room
        JRadioButton doubleRoom = new JRadioButton("Double Room");
        //add an actionlistener to the radio button
        doubleRoom.addActionListener(this);
        //set selected to true
        doubleRoom.setSelected(true);
        //set roomType to 0
        roomType = 0;
        //add double room to myjpanel
        myJPanel.add(doubleRoom);

        //make new jradiobutton called 
        JRadioButton suite = new JRadioButton("Suite");
        //add an action listener to suite
        suite.addActionListener(this);
        //add suite to myjpanel
        myJPanel.add(suite);

        //initialize new buttongroup called group
        ButtonGroup group = new ButtonGroup();
        //add doubleroom to the group
        group.add(doubleRoom);
        //add suite to the group
        group.add(suite);


        //make new button
        button = new JButton("Enter Information");
        //add action listener to the button
        button.addActionListener(this);
        //add button to jpanel
        myJPanel.add(button);

        //remove everything from the content pane
        contentPane.removeAll();
        //add myjpanel to the content pane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: informationError
     * Program Description: tells user what errors may have occurred with the information they entered
     * Inputs: 
     * Outputs: error messages
     */
    public void informationError(){
        //get the content pane
        contentPane = getContentPane();
        //initialize new jpanel
        myJPanel = new JPanel();

        //make new JLabel with an error message
        L1 = new JLabel("Please insure that your credit card number is in the form of ####-####-####");
        //add l1 to myjpanel
        myJPanel.add(L1);

        //make new JLabel with an error message
        L1 = new JLabel("Please insure that your phone number is the in form ########## (10 numbers, include area code)");
        //add l1 to myjpanel
        myJPanel.add(L1);

        //make new JLabel with an error message
        L1 = new JLabel("Please insure that a number was entered for the number of days");
        //add l1 to myjpanel
        myJPanel.add(L1);

        //remove everything from the content pane
        contentPane.removeAll();
        //add myjpanel to the content pane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: enterInformation
     * Program Description: enters the information that the user input
     * Inputs: text from text fields and roomtype
     * Outputs: new client with information
     */
    public void enterInformation(){
        //set go to true
        boolean go = true;
        // set len to 0
        int len = 0;

        //if checkfull returns false
        if(!checkFull(roomType)){

            //test the follwoing code
            try{
                //set len to the integer value of the text from length
                len = Integer.valueOf(length.getText()).intValue();
            //catch the numberformat exception
            } catch (NumberFormatException e) {
                //set go to false
                go = false;
            }

            //if checkcardnumber returns false
            if(!checkCardNumber(cardNumber.getText())){
                //set go to false
                go = false;
            }

            //if checkphonenumber returns false
            if(!checkPhoneNumber(phoneNumber.getText())){
                //set go to false
                go = false;
            }

            //if go is true
            if(go){
                //make new client called temp
                Client temp = new Client();
                //add a new client to the clientlist
                clientList.add(new Client());
                //add one to counter
                counter++;
        
                //set temp name to the text in name
                temp.name = name.getText();
        
                //set temp creditnumber to the text in cardnumber
                temp.creditNumber = cardNumber.getText();
        
                //set temp phonenumber to the text in phonenumber
                temp.phoneNumber = phoneNumber.getText();
        
                //set temp daysstaying to len
                temp.daysStaying = len;
            
                //set temp roomtype to roomtype
                temp.roomType = roomType;
    
                //set time entered to the current time in milliseconds
                temp.timeEntered = System.currentTimeMillis();

                //set timeleaving the sum of time entered and the number of days the client will stay in milliseconds
                temp.timeLeaving = Long.sum(temp.timeEntered, (long)(24*60*60*1000*temp.daysStaying));
    
                //set clientlist at counter-1 to temp
                clientList.set(counter-1, temp);
    
                //run pickroom with counter-1
                pickRoom(counter-1);

                //sort the clients
                sortClients();

                //test the following code
                try{
                    //run save clients
                    saveClients();
                //catch an IOException
                }catch(IOException e){
                    //print an ioexception occured
                    System.out.println("There has been an IOException");
                }
                //run addclient
                addClient();
            //else
            } else{
                //run informationerror
                informationError();
            }
        //else
        } else{
            //get content pane
            contentPane = getContentPane();
            //initialize jpanel
            myJPanel = new JPanel();

            //tell user that the rooms are full
            L1 = new JLabel("Those rooms are full");
            //add l1 to myjpanel
            myJPanel.add(L1);

            //remove everything from the content pane
            contentPane.removeAll();
            //add the jpanel to the content pane
            contentPane.add(myJPanel);
            //validate
            validate();
        }
        //test the following code
        try{
            //run saverooms
            saveRooms();
        //catch any ioexception
        }catch(IOException e){
            //print the an ioexception occured
            System.out.println("ioexception occured");
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: removeWindow
     * Program Description: allows user to type the name of the user that they would like to remove
     * Inputs: client to be removed
     * Outputs: prompt
     */
    public void removeWindow(){
        //get the content pane
        contentPane = getContentPane();
        //initialize jpanel
        myJPanel = new JPanel();

        //make new jpanel prompting user to input name
        L1 = new JLabel("Enter the name of the client you would like to remove");
        //add l1 to the jpanel
        myJPanel.add(L1);

        //make new jtextfield of size 20
        name = new JTextField(20);
        //add name to the jpanel
        myJPanel.add(name);

        //make new button that says remove
        button = new JButton("Remove");
        //add action listener to button
        button.addActionListener(this);
        //add button to the jpanel
        myJPanel.add(button);

        //remove everything from the jpanel
        contentPane.removeAll();
        //add myjpanel to the content pane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: removeClient
     * Program Description: removes a client from the list
     * Inputs: client to be gotten rid of
     * Outputs: updated client list
     */
    public void removeClient(){
        //set key to the text in name
        String key = name.getText();
        //set flag to -1
        int flag = -1;

        //for i from 0 to the size of the client list
        for(int i = 0; i < clientList.size(); i++){
            //if the clientlist at i's name is the same as the key
            if(clientList.get(i).name.equals(key)){
                //set flag to i
                flag = i;
                //set rooms at the client's room number -1 .full to false
                rooms[clientList.get(i).roomNumber - 1].full = false;
                //remove the client from the list
                clientList.remove(i);
                //subtrcat 1 form counter
                counter--;
            }
        }

        //if the flag is -1
        if(flag == -1){
            //run not found
            notFound();
        //else
        }else {
            //test the following code
            try{
                //run saverooms
                saveRooms();
                //run saveclients
                saveClients();
            //catch ioexception
            }catch(IOException e){
                //print there has been an ioexception
                System.out.println("there has been an ioexception");
            }
            //run removewindow
            removeWindow();
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: notFound
     * Program Description: tells user that the client could not be found
     * Inputs: 
     * Outpus: the client could not be found
     */
    public void notFound(){
        //get the content pane
        contentPane = getContentPane();
        //initialize new jpanel
        myJPanel = new JPanel();

        //make new label that says the client could not be found
        L1 = new JLabel("That Client could not be found");
        //add l1 to the jpanel
        myJPanel.add(L1);

        //remove everything from the contetn pane
        contentPane.removeAll();
        //add myjpanel to the content pane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: viewCLients
     * Program Description: shows a table of the all the clients and their information
     * Inputs: client inforamtion
     * Outpus: client table
     */
    public void viewClients(){
        //get the content pane
        contentPane = getContentPane();
        // make new jpanel
        myJPanel = new JPanel();

        //set the column names to the information to be shown
        String[] columnNames = {"Name", "Number", "Credit Card", "Days Staying", "Room Type", "Room Number"};
        //make new 2d string array
        String[][] clientInfo = new String[counter][6];

        //for i from 0 to counter
        for(int i = 0; i < counter; i++){
            //set clientinfo at i,0 to clientlist at i's name
            clientInfo[i][0] = clientList.get(i).name;
            //set clientinfo at i,1 to clientlist at i's pheonnumber
            clientInfo[i][1] = clientList.get(i).phoneNumber;
            //set clientinfo at i,2 to clientlist at i's creditNumber
            clientInfo[i][2] = clientList.get(i).creditNumber;
            //set clientinfo at i,3 to clientlist at i's number of days staying
            clientInfo[i][3] = Integer.toString(clientList.get(i).daysStaying);
            //if the roomtype is 0
            if(clientList.get(i).roomType == 0) {
                //set clientinfo at i,4 to double room
                clientInfo[i][4] = "Double Room";
            //else roomtype is 1
            }else{
                //set clientinfo at i,4 to suite
                clientInfo[i][4] = "Suite";
            }
            //set clietn info at 5 to the clients roomnumber
            clientInfo[i][5] = Integer.toString(clientList.get(i).roomNumber);
        }

        //set L1 to a new jlabel that says clients
        L1 = new JLabel("Clients");
        //add l1 to myjpanel
        myJPanel.add(L1);

        //set table to a jtable with clientinfo and columnnames
        table = new JTable(clientInfo, columnNames);
        //set scrollpane to the new jscrollpane with table
        JScrollPane scrollPane = new JScrollPane(table);

        //add srollpane to myjpanel
        myJPanel.add(scrollPane);

        //remove everything form content pane
        contentPane.removeAll();
        //add myjpanel to content pane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: initializeRooms
     * Program Description: loads room information from roominfo.txt
     * Inputs: roominfo.txt
     * Outputs: array of rooms
     */
    public void initializeRooms()throws IOException{
        //set load to null
        BufferedReader load = null;
        //test the following code
        try{
            //set load to a new buffered file reader for roominfo.txt
            load = new BufferedReader(new FileReader("RoomInfo.txt"));
        //catcg the filenotfound exception
        }catch(FileNotFoundException e){
            //print that file could not be found
            System.out.println("That File could not be found");
        }
        
        //for   
        for(int i = 0; i < 200; i++) {
            //set rooms at i to a new double room with a rooom number of i +1
            rooms[i] = new DoubleRoom(i+1);
        }

        //for i from 200 to 300
        for(int i = 200; i < 300; i++) {
            //set rooms at i to a new suit with a rooom number of i +1
            rooms[i] = new Suite(i+1);
        }

        //for i from 0 to 300
        for(int i = 0; i < 300; i++) {
            //set rooms at i dirty to the next line
            //if null will return false
            rooms[i].dirty = Boolean.parseBoolean(load.readLine());
            //set rooms at i full to the next line
            //if null will return false
            rooms[i].full = Boolean.parseBoolean(load.readLine());
        }
        //close load
        load.close();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: saveRooms
     * Program Description: saves all the information about the rooms
     * Inputs: rooms array
     * Outpus: txt file with room information
     */
    public void saveRooms() throws IOException{
        //set save to null
        PrintWriter save = null;

        //tes following code
        try{
            //set save to new printwriter, filewriter of roominfo.txt
            save = new PrintWriter(new FileWriter("RoomInfo.txt"));
        //catch the file not found exception
        }catch(FileNotFoundException e){
            //print that file could not be found
            System.out.println("That file could not be found");
        }

        //for i form 0 to  300
        for(int i = 0; i < 300; i++){
            //print dirty from rooms at i
            save.println(rooms[i].dirty);
            //print full form rooms at i
            save.println(rooms[i].full);
        }

        //close save
        save.close();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: pickRoom
     * Program Description: Gives client a roomnumber
     * Inputs: roomtype clientnumber
     * Outputs: client's room number
     */
    public void pickRoom(int clientNumber) {
        //set test to true
        boolean test = true;
        //set roomNumber to -1
        int roomNumber = -1;
        //while test is true
        while(test){
            //if the room type of the client is 0
            if(clientList.get(clientNumber).roomType == 0){
                //set roomnumber to a random number from 1 ot 199
                roomNumber = (int) (Math.random()*199+1);
                //if rooms at room number-1 is empty
                if(rooms[roomNumber-1].full == false){
                    //set test to false
                    test = false;
                }
            //else must be type 1
            }else{
                //set room number to a random number from 200 to 300
                roomNumber = (int) (Math.random()*101+200);
                //if rooms at room number is empty
                if(rooms[roomNumber-1].full == false){
                    //set test to false
                    test = false;
                }
            }
        }
        //set the room number of the client
        clientList.get(clientNumber).setRoomNumber(roomNumber);
        //set the room at room number to full
        rooms[roomNumber-1].full = true;
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: checkFull
     * Program Description: checks if all the rooms of a certain type are full
     * Inputs: roomtype
     * Outputs: whther full or not
     */
    public boolean checkFull(int roomType){
        //if roomtype is 0
        if(roomType == 0){
            //for i from 0 to 200
            for(int i = 0; i < 200; i++){
                //if rooms at i is empty
                if(rooms[i].full == false){
                    //return false
                    return false;
                }
            }
            //return true
            return true;
        //else roomtype is 1
        } else{
            //for i from 200 to 300
            for(int i = 200; i < 300; i++){
                //if rooms at i is empty
                if(rooms[i].full == false){
                    //return false
                    return false;
                }
            }
            //return true
            return true;
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: saveClients
     * Program Description: saves client information to a text file
     * Inputs: Client information
     * Outputs: client information in a text file
     */
    public void saveClients()throws IOException{
        //set save to null
        PrintWriter save = null;

        //test the following code
        try{
            //set save to a new printwriter, filewriter for clientinfo.txt
            save = new PrintWriter(new FileWriter("ClientInfo.txt"));
        //catch the filenotfoundexcetion
        }catch(FileNotFoundException e){
            //print that file could not be found
            System.out.println("That file could not be found");
        }
        //print the counter
        save.println(counter);
        
        //for i from 0 to counter
        for(int i = 0; i < counter; i++){
            //print client at i's creditnumber
            save.println(clientList.get(i).creditNumber);
            //print client at i's daysstaying
            save.println(clientList.get(i).daysStaying);
            //print client at i's name
            save.println(clientList.get(i).name);
            //print client at i's phonenumber
            save.println(clientList.get(i).phoneNumber);
            //print client at i's roomnumber
            save.println(clientList.get(i).roomNumber);
            //print client at i's roomtype
            save.println(clientList.get(i).roomType);
            //print client at i's timeentered
            save.println(clientList.get(i).timeEntered);
            //print client at i's timeleaving
            save.println(clientList.get(i).timeLeaving);
        }
        //close save
        save.close();

    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: loadClients
     * Program Description: loads client information from a text file
     * Inputs: client information from text file
     * Outputs: client list
     */
    public void loadClients()throws IOException{
        //set load to null
        BufferedReader load = null;

        //test the following code
        try{
            //set load to a new buffered file reader for clientinfo.txt
            load = new BufferedReader(new FileReader("ClientInfo.txt"));
        //catch the filenotfoundexception
        }catch(FileNotFoundException e){
            //print that file could not be found
            System.out.println("That file could not be found");
        }

        //test the follwing code
        try{
            //set counter to the integer value of the first line
            counter = Integer.valueOf(load.readLine()).intValue();
        //catch the numberformatexception
        }catch(NumberFormatException e){
            //set counter to 0
            counter = 0;
        }
        
        //for i from 0 to counter
        for(int i = 0; i < counter; i++){
            //make new client called temp
            Client temp = new Client();
            
            //set temp's credit number too the next line of the text file
            temp.creditNumber = load.readLine();
            //set temp's daysstaying to the output of checkint of the next line
            temp.daysStaying = checkInt(load.readLine());
            //set temp's name too the next line of the text file
            temp.name = load.readLine();
            //set temp's phone number too the next line of the text file
            temp.phoneNumber = load.readLine();
            //set temp's roomNumber to the output of checkint of the next line
            temp.roomNumber = checkInt(load.readLine());
            //set temp's roomtype to the output of checkint of the next line
            temp.roomType = checkInt(load.readLine());
            //set temp's timeEntered to the output of checkLong of the next line
            temp.timeEntered = checkLong(load.readLine());
            //set temp's timeLeaving to the output of checkLong of the next line
            temp.timeLeaving = checkLong(load.readLine());

            //add a new client to the clientlist
            clientList.add(new Client());
            //set client list at i to temp
            clientList.set(i, temp);
        }
        //close load
        load.close();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: checkInt
     * Program Description: ttakes a string and insures it can be turned into an integer
     * Inputs: string
     * Outputs: integer
     */
    public int checkInt(String number){
        //set output to 0
        int output = 0;

        //test follwoing code
        try{
            //set output to the integervalue of number
            output = Integer.valueOf(number).intValue();
        //catch the numberformatexception
        }catch(NumberFormatException e){
            //set ouput to 0
            output = 0;
        }

        //return output
        return output;
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: checkLong
     * Program Description: ttakes a string and insures it can be turned into an long
     * Inputs: string
     * Outputs: long
     */
    public long checkLong(String number){
        //set output to 0
        long output = 0;

        //test the follwoing code
        try{
            //set output to the long value of number
            output = Long.valueOf(number).longValue();
        //catch the number format exception
        }catch(NumberFormatException e){
            //set output to 0
            output = 0;
        }

        //return output
        return output;
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: update
     * program Description: runs on a timer and updates various values
     * Inputs:
     * Outputs: runs two methods
     */
    public void update(){
        //run make dirty
        makeDirty();
        //run leave
        leave();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: makeDirty
     * Program Description: makes a room dirty if it has been a multiple of a day since entered
     * Inputs:time entered
     * Outputs: dirty rooms
     */
    public void makeDirty(){
        //for i from 0 to counter
        for(int i = 0; i < counter; i++) {
            //if the current time is more than a day past entered
            if(!(System.currentTimeMillis() - clientList.get(i).timeEntered < 86400000)){
                //if the time stayed is a multiple of 24 hours give or take 20 seconds (2 update cycles)
                if((System.currentTimeMillis() - clientList.get(i).timeEntered) % 86400000 < 20000){
                    //make room dirty
                    rooms[clientList.get(i).roomNumber - 1].makeDirty();
                }
            }
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: cleanRooms
     * Program Description: allows user to see the state of rooms and declare them clean
     * Inputs: array of room information
     * outputs: table of room states
     */
    public void cleanRooms(){
        //make an integer arraylist of rmnumbers
        ArrayList<Integer> rmNumbers= new ArrayList<Integer>();

        //get the contentapne
        contentPane = getContentPane();
        //make new jpanel
        myJPanel = new JPanel();

        //for i from 0 to counter
        for(int i = 0; i < counter; i++){
            //add client's roomnumber to rmnumbers
            rmNumbers.add(i, clientList.get(i).roomNumber);
        }

        //make string array of headers
        String[] headers = {"Room Number", "Dirty"};

        //make 2d string array of rooms
        String[][] rooms = new String[rmNumbers.size()][2];

        //for i from 0 to the size of rmnumbers
        for(int i = 0; i < rmNumbers.size(); i++){
            //set rooms at i,0 to the rmnumbers at i
            rooms[i][0] = Integer.toString(rmNumbers.get(i));
            //set rooms at i,0 to the value of dirty for the room at roomnumber-1
            rooms[i][1] = Boolean.toString(this.rooms[rmNumbers.get(i) - 1].dirty);
        }

        //make new jtable with rooms and headers
        table = new JTable(rooms, headers);

        //make new jscrollpane with table
        JScrollPane scrollPane = new JScrollPane(table);
        //ad the scrollpane to myjpanel
        myJPanel.add(scrollPane);

        //add a mouselistener with mouse adapter to the table
        table.addMouseListener(new MouseAdapter(){

            //gets row
            public void mouseClicked(MouseEvent e){
                //set row to the selected row in table
                row = table.getSelectedRow();
            }
        });

        //make new button called clean
        button = new JButton("Clean");
        //add action listener to the button
        button.addActionListener(this);
        //add button to myjpanel
        myJPanel.add(button);

        //remove everything from the content pane
        contentPane.removeAll();
        //add myjpanel to the content pan
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: clean
     * Program Description: cleans a room
     * Inputs: row
     * Outputs; Clean room
     */
    public void clean(){
        //clean the room at the room number on the row that has been clicked
        rooms[Integer.valueOf((String)(table.getModel().getValueAt(row, 0))).intValue()-1].cleanRoom();
        //run cleanrooms
        cleanRooms();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: leave
     * Program Description: removes clients that have left
     * Inputs: time client is leaving
     * Outputs: one less client if it is time for thme to leave
     */
    public void leave(){
        //for i from 0 to counter
        for(int i = 0; i < counter; i++){
            //if the current time in milliseconds is greater than the time leaving
            if(System.currentTimeMillis() > clientList.get(i).timeLeaving){
                //empty room
                rooms[clientList.get(i).roomNumber - 1].full = false;
                //clean room
                rooms[clientList.get(i).roomNumber - 1].dirty = false;
                //remove client
                clientList.remove(i);
                //subtract one from counter
                counter--;
            }
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: sortClients
     * Program Description: sorts client alphabectically by name
     * Inputs: unsorted clients
     * Outputs: sorted clients
     */
    public void sortClients(){
        //set swapped to true
        boolean swapped = true;
        //temp client
        Client temp = new Client();

        //while swapped is true
        while(swapped) {
            //set swapped to false
            swapped = false;

            //for i from 1 to counter
            for(int i = 1; i < counter; i++){
                //if cleint list's name at i [5] is greater than i-1 [5]
                if(clientList.get(i).name.compareTo(clientList.get(i-1).name) < 0) {
                    //set temp to clientlist at i
                    temp = clientList.get(i);
                    //set clientlist at i to clientlist at i-1
                    clientList.set(i, clientList.get(i-1));
                    //set clientlist at i-1 to temp
                    clientList.set(i - 1, temp);

                    //set swapped to true
                    swapped = true;
                }
            }
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: searchCLients
     * Program Description: searchClients
     * Inputs: search term
     */
    public void searchClients(){
        //get content pane
        contentPane = getContentPane();
        //make new jpanel
        myJPanel = new JPanel();

        //set l1 to promt for client name
        L1 = new JLabel("What is the name of the client you would like to search for");
        //add l1 to the jpanel
        myJPanel.add(L1);

        //make new text field
        name = new JTextField(20);
        //add name to my jpanel
        myJPanel.add(name);

        //make button called search
        button = new JButton("Search");
        //add actionlistener to button
        button.addActionListener(this);
        //add button to myjpanel
        myJPanel.add(button);

        //remove everything from the content pane
        contentPane.removeAll();
        //add myjpanel to contenpane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: search
     * Program Description: Shows results of the search
     * inputs: output of binary search
     * Outputs: room number of client searched for
     */
    public void search(){
        //get the content pane
        contentPane = getContentPane();
        //set myjpanel to a new jpanel
        myJPanel = new JPanel();

        //set vaule to binarySearch
        int value = binarySearch();

        //if value is -1
        if(value == -1){
            //run not found
            notFound();
        } else {
            //make new jlabel that says the clients room number
            L1 = new JLabel("Their Room Number is: " + clientList.get(value).roomNumber);
            //add l1 to the jpanel
            myJPanel.add(L1);
        }

        //remove everything form the content pane
        contentPane.removeAll();
        //add myjpanel to the content pane
        contentPane.add(myJPanel);
        //validate
        validate();
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: binarySearch
     * Program Description: chooses random key and does a search
     * Inputs: sorted array
     * Outputs: the output of a binarySearch
     */
    public int binarySearch(){
        //set key to the text in name
        String key = name.getText();
        //calls binarySearch with explicit low and high input parameters
        return binarySearch(key, 0, clientList.size() - 1);
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: binarySearch
     * Program Description: does a binary search
     * Inputs: sorted array, key, low input, high input
     * Outputs: the output of a binarySearch, -1, or mid
     */
    public int binarySearch(String key, int low, int high){
        //if low is greater than high
        if (low > high){
            //return -1
            return - 1;
        }

        //set mid to low + high divided by 2
        int mid = (low + high) / 2;
        
        //if mid is the search key
        if (clientList.get(mid).name.compareToIgnoreCase(key) == 0){
            //output mid
            return mid;
        //if mid was less than the search key
        } else if (clientList.get(mid).name.compareToIgnoreCase(key) < 0){
            //output the output of binary search with an array, a key, a low and a high
            return binarySearch(key, mid + 1, high);
        //otherwise mid was greater than the search key
        } else  {
            //output the output of a binary search with an array, a key, a low and a high
            return binarySearch(key, low, mid - 1);
        }
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: checkCardNumber
     * Program Description: Check to insure the card number has the correct format
     * Inputs: cardnumber
     * Outputs: boolean whether or not card number fit
     */
    public boolean checkCardNumber(String cardNumber){
        //set regex to a regular expression describing the format of a credit card number
        String regex = "^([0-9]{4})-([0-9]{4})-([0-9]{4})$";

        //return whether or not the cardnumber match the regular expression
        return cardNumber.matches(regex);
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: checkPhoneNumber
     * Program Description: Check to insure the phone number has the correct format
     * Inputs: phonenumber
     * Outputs: boolean whether or not phone number fit
     */
    public boolean checkPhoneNumber(String phoneNumber){
        //set regex to a regular expression describing the format of a phonenumber
        String regex = "^([0-9]{10})$";

        //return whether or not the phonenumber match the regular expression
        return phoneNumber.matches(regex);
    }
}