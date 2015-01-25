import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by cau19 on 15/01/11.
 *
 * Circuit main purpose besides holding the stats for the circuit is to keep
 * a list of jugglers in a priority queue to always allow a quick access to
 * the weakest member.
 */
public class Circuit {

    //The circuit ID
    private String id;

    //ints for the stats of the circuit
    private int handEye;
    private int endurance;
    private int pizzazz;
    //max members for the circuit
    private int maxMembers;

    //A priorityQueue to hold the jugglers. This always us quick access and maintain the
    //weakest juggler
    private PriorityQueue<Juggler>  jugglers;


    /**
     * Comparator for the priorityQueue. It simple takes two jugglers and compares if it's greater, equal, or less.
     */
    class JugglerCompare implements Comparator<Juggler>{
        Circuit circuit;
        //constructor needs to take a circuit so we can take the dot product.
        JugglerCompare(Circuit c){
            this.circuit =c;
        }
        public int compare(Juggler x, Juggler y){
            return x.getDotProduct(this.circuit) - y.getDotProduct(this.circuit);
        }
    }

    /**
     * Create a circuit with its id and stats from the file
     * @param id of the circuit
     * @param handENum of the circuit
     * @param endurance of the circuit
     * @param pizzNum pizzazz of the circuit
     */
    Circuit(String id, int handENum, int endurance, int pizzNum){
        this.id = id;
        this.handEye = handENum;
        this.endurance = endurance;
        this.pizzazz = pizzNum;
    }

    //getters

    /**
     * get the list of jugglers to take the sum for circuit 1970
     * @return
     */
    public PriorityQueue<Juggler> getJugglers(){ return this.jugglers;}

    /***
     * get the ID of the circuit
     * @return the id of the circuit
     */
    public String getId() {return this.id;}

    /**
     * get the handEye of the circuit. Used for the dot product.
     * @return
     */
    public int getHandEye(){
        return this.handEye;
    }

    /**
     * get the endurance of the circuit. Used for the dot product.
     * @return
     */
    public int getEndurance(){
        return this.endurance;
    }

    /**
     * get the pizzazz of the circuit. Used for the dot product.
     * @return
     */
    public int getPizzazz(){
        return  this.pizzazz;
    }

    /**
     *  Get if the circuit is full or not.
     * @return
     */
    public boolean isNotFull(){
        return this.jugglers.size()!=maxMembers;
    }

    /**
     * Returns the list of jugglers in the circuit along with the circuit id.
     * @return
     */
    public String toString(){
        String list = "";
        for (Juggler j : jugglers){
            list= list+ " "+j.toString();
        }
        String removedComma= list.substring(0, list.length()-1);
        return this.getId()+ removedComma;
    }


    /**
     * return the weakest juggler in the priority queue.
     * @return
     */
    public Juggler peek(){
        return this.jugglers.peek();
    }

    /**
     * remove the weakest juggler from the circuit and return it. Don't worry, he gets added to a new circuit :)
     * @return
     */
    public Juggler removeWeakJuggler(){
        //it shouldn't be empty but just in case
        if(!jugglers.isEmpty()) {
            return jugglers.remove();
        }
        return null;
    }

    //setters

    /**
     * set the maximum number of members for a circuit.
     * @param max an int to be passed with the max members
     */
    public void setMaxMembers(int max){
        maxMembers = max;
        JugglerCompare comparator = new JugglerCompare(this);
        this.jugglers = new PriorityQueue<Juggler>(max, comparator);

    }

    /**
     * add the Juggler to the circuit
     * @param j a juggler
     */
    public void addJuggler(Juggler j){
        this.jugglers.add(j);
    }
}
