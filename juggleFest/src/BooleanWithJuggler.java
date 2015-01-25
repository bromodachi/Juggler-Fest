/**
 * Created by cau19 on 15/01/11.
 *
 * This class is just to indicate whether or not a juggler was added successfully
 * If it wasn't added successfully, we pass a juggler and indicate it's false.
 * If a juggler was added successfully, we pass a true and a null juggler.
 */
public class BooleanWithJuggler {
    /**
     * Success: was a juggler added successfully?
     */
    private boolean success;
    /**
     * leftOverJuggler:if a juggler couldn't join a team, pass it to add
     * it to the left over juggler lists.
     */
    private Juggler leftOverJuggler;

    /**
     * Create a boolean with the left over juggler if it wasn't added
     * to a list. False indicates a left over juggler exists
     * @param suc true or false if the juggler was added or not
     * @param j the left over juggler that couldn't be added. Null if it was
     */
    public BooleanWithJuggler(boolean suc, Juggler j){
        this.success= suc;
        this.leftOverJuggler = j;
    }

    /**
     * gets a boolean that indicates if a juggler was added or not
     * true if it was, false if it wasn't
     * @return true or false
     */
    public boolean getSuccess(){
        return  this.success;
    }

    /**
     * get the left over juggler if that juggler was not added successfully
     * @return Juggler
     */
    public Juggler getLeftOverJuggler(){
        return this.leftOverJuggler;
    }

}
