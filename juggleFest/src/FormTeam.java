import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cau19 on 15/01/11.
 *
 * Form the team of jugglers that best suits a circuit
 */
public class FormTeam {

    /**
     * List of circs that is given through parsefile
     */
    private ArrayList <Circuit> circs;
    /**
     * list of jugglers that is given through parsefile
     */
    private ArrayList <Juggler> jugglers;
    /**
     * A queue of leftOverJugglers. Less space in memory and
     * we're going to deque them all of them.
     */
    private Queue<Juggler> leftoverJugglers;

    /**
     * FormTeam takes the circuit and juggler list to start creating teams
     * @param c list of circuits
     * @param j list of jugglers
     */
    public FormTeam(ArrayList<Circuit> c, ArrayList <Juggler> j){
        this.circs = c;
        this.jugglers  = j;
        leftoverJugglers = new LinkedList<Juggler>();
    }

    /**
     * addJugglers iterates through each juggler in both the juggler and the leftover lists
     */
    public void addJugglers(){
        //for each juggler, attempt to add him
        for(Juggler j: jugglers){
            BooleanWithJuggler getBool =j.addJugglerToCircuit();
            //if we couldn't add him, add him to the left over list
            if(!getBool.getSuccess()){
                leftoverJugglers.add(getBool.getLeftOverJuggler());
            }
        }
        //for each left over juggler, just add him to the circuit.
        while(!leftoverJugglers.isEmpty()) {
            Juggler j = leftoverJugglers.remove();
                for (Circuit c : circs) {
                    //Only if the circuit is not full, add the juggler to this circuit.
                    while (c.isNotFull()) {
                       c.addJuggler(j);
                    }
                }

        }
    }

    /**
     * Prints out the sum and creates the output file based on the criteria of the puzzle
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
   public void printOutput() throws FileNotFoundException, UnsupportedEncodingException {
       int sum = 0;
       PrintWriter writer = new PrintWriter("outPut.txt", "UTF-8");
       for (Circuit c : circs) {
           //If it's the 1970, we need the sum to send the email to
           if (c.getId().equals("C1970")) {
               //for each juggler, get his id number and get the sum
               for (Juggler j : c.getJugglers()) {
                   sum += j.getIdNum();
               }
           }
           //write each circuit to the output
               writer.println(c.toString());
           //always good to flush the writer after a print
               writer.flush();

       }
       //always close a writer
       writer.close();
       //print out the sum
       System.out.println("The sum to the Juggler IDs to send the email is : " + sum);
   }
}
