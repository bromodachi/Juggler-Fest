import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cau19 on 15/01/11.
 *
 * Juggler holds a juggler's ID, handeye, endurance, pizzazz, a list of circuits he wants to join
 * contained in a queue to deque his preference and save looping the same list over again.
 * As well as a LinkedList as backup. This backup is only for the printing part.
 */
public class Juggler {
    /**
     * A juggler's id. For creating the output
     */
    private String jugglerID;
    /**
     * Following are the stats of the jugglers. His stats determine if he can join circuit of his choice if he
     * is good enough for that circuit.
     */
    private int handEye;
    private int endurance;
    private int pizzazz;

    /**
     * Keep the list of his preference circuit in a queue and deque it when needed.
     */
    private Queue<Circuit> preferenceCircuit;
    /**
     * A back up of his preference circuit. We need it to print his dot product for each of his preference circuit
     * and output it in a file
     */
    private LinkedList<Circuit> keepListOfCicuits;

    /**
     * create a juggler with his id and his stats.
     * @param id
     * @param handEye
     * @param endurance
     * @param pizzazz
     */
    public Juggler(String id, int handEye, int endurance, int pizzazz){
        this.jugglerID = id;
        this.handEye = handEye;
        this.endurance = endurance;
        this.pizzazz = pizzazz;
        preferenceCircuit = new LinkedList<Circuit>();
        keepListOfCicuits = new LinkedList<Circuit>();
    }

    /**
     * over write the toString java's method to neatly print out his circuit score for each of his
     * preference circuits. The backup list is used here. This is for creating the output text
     * @return juggler's id and his circuit match score for each circuit in his preference list
     */
    public String toString(){
        String circuitMatchScore = "";
        for(Circuit c : keepListOfCicuits) {
            circuitMatchScore = circuitMatchScore+ " "  + c.getId() + ":" + getDotProduct(c);
        }
        return this.jugglerID + circuitMatchScore +",";
    }

    /**
     * get the juggler's id but only the number.
     * @return
     */
    public int getIdNum(){
        String id = this.jugglerID.replace("J", "");
        return Integer.parseInt(id);
    }

    /**
     * get the dot product of the juggler to see if he is the best for the circuit
     * @param c circuit passed to take the dot product with the juggler
     * @return and int of the juggler's ability to perform for this circuits
     */
    public int getDotProduct(Circuit c){
        return c.getHandEye() * this.handEye + c.getEndurance()*this.endurance + c.getPizzazz()*this.pizzazz;
    }

    /**
     * Add the circuits to both the preference and duplicate lists
     * @param circ a circuit of the juggler's preference list from the file.
     */
    public void addCircuitToPreferenceList(Circuit circ){
        preferenceCircuit.add(circ);
        keepListOfCicuits.add(circ);
    }

    /**
     * Attempts to add a juggler to a circuit in his preference list.
     * If the circuit in his preference list is not empty, add the juggler right away.
     *
     * If it's full, get the dot product and if the juggler is better for this circuit, kick the
     * weakest and attempt to find a circuit for him(through his preference list.)
     * if he wasn't added successfully, he will be added to the left over list.
     *
     * if this current juggler couldn't be added to a list, he gets added to the left over list.
     * @return a boolean with or without a juggler that couldn't be addeds
     */
    public  BooleanWithJuggler addJugglerToCircuit(){
        while(!preferenceCircuit.isEmpty()) {
            Circuit c = preferenceCircuit.remove();
            //if it's not full, immediately add the juggler
            if (c.isNotFull()) {
                c.addJuggler(this);
                return  new BooleanWithJuggler(true, null);
            }
            //is full? Is this juggler better?
            else if(c.peek().getDotProduct(c)<this.getDotProduct(c)){
                    Juggler j= c.removeWeakJuggler();
                    c.addJuggler(this);
                    return j.addJugglerToCircuit();

            }
        }
        //if he couldn't be added, indicate that we couldn't add him and return him to
        //add him to the left over list
        return  new BooleanWithJuggler(false, this);
    }

}
