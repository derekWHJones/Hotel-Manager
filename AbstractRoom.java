/*
 * Programmer: Derek Jones
 * Date: 2019-07-25
 * Program Name: AbsrtactRoom
 * Program Description: hold information for a room
 */
public class AbstractRoom{
    //number of the room
    public int roomNumber;
    //whether or not the room is dirty
    public boolean dirty = false;
    //whether or not the room is full
    public boolean full = false;

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: cleanRoom
     * Program Description: cleans a room
     * Inputs: dirty
     * Outputs: dirty
     */
    public void cleanRoom(){
        //set dirty to false
        dirty = false;
    }

    /*
     * Programmer: Derek Jones
     * Date: 2019-07-25
     * Program Name: makeDirty
     * Program Description: makes a room dirty
     * Inputs: full, dirty
     * Outputs: dirty
     */
    public void makeDirty(){
        //if the room is full
        if(full == true) {
            // set dirty to true
            dirty = true;
        }
    }
}