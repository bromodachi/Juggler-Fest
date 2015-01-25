import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by cau19 on 15/01/11.
 *
 * ParseFile's job is just to parse the jugglefest.txt
 * and add all circuits and jugglers in the array.
 */
public class ParseFile {
    /**
     * reader is just to read the txt file passed in the argument
     */
    private BufferedReader reader;

    /**
     * Holds the list of circuits contained in the file to later form the team
     */
    private ArrayList <Circuit> circs = new ArrayList<Circuit>();
    /**
     * Holds the list of jugglers contained in the file to later form a team
     */
    private ArrayList <Juggler> jugglers = new ArrayList<Juggler>();


    /**
     * Create a ParseFile with the file to read.
     * @param r the file we should read
     */
    public ParseFile(FileReader r){
        reader = new BufferedReader(r);

    }

    /**
     * parseFile reads each line, tokenize the lines and then convert certain values into ints to create
     * the circuit or jugglers
     */
    public void parseFile(){
        String line;
        try {
            while ((line = reader.readLine()) != null ){
                //create a tokenizer to extract certain data
                StringTokenizer tokenizer = new StringTokenizer(line);
                //iterate each token
                while(tokenizer.hasMoreTokens()){
                    String token = tokenizer.nextToken();

                    //we are currently doing the circuits.
                    if(token.equals("C")){

                        //get the strings of the id, handeye, endurance, and pizzazz
                        String id = tokenizer.nextToken();
                        String handEye  = tokenizer.nextToken();
                        String endurance = tokenizer.nextToken();
                        String pizzazz  = tokenizer.nextToken();

                        //once we have the certain string values, remove the ids, and parse the ints.
                        int handEyeInt = Integer.parseInt(handEye.replace("H:", ""));
                        int eduranceInt = Integer.parseInt(endurance.replace("E:", ""));
                        int pizzazzInt = Integer.parseInt(pizzazz.replace("P:", ""));

                        //finally, we can create the circuit
                        Circuit circuit = new Circuit(id, handEyeInt,
                                eduranceInt,pizzazzInt );
                        //and add it to the list
                        circs.add(circuit);

                    }
                    else{
                        //we're at the jugglers.
                        String id = tokenizer.nextToken();

                        //get the strings of the id, handeye, endurance, and pizzazz for the juggler
                        String handEye  = tokenizer.nextToken();
                        String endurance = tokenizer.nextToken();
                        String pizzazz  = tokenizer.nextToken();

                        //convert the string values once again to ints while removing the ids.
                        int handEyeInt = Integer.parseInt(handEye.replace("H:", ""));
                        int eduranceInt = Integer.parseInt(endurance.replace("E:", ""));
                        int pizzazzInt = Integer.parseInt(pizzazz.replace("P:", ""));

                        //for the list of preference, extract the whole thing as a string
                        String listOfPreferences  = tokenizer.nextToken();

                        //then use java's split to hold them in an array
                        String [] arrayOfPreferences = listOfPreferences.split(",");

                        //then create the juggler first
                        Juggler j = new Juggler(id, handEyeInt,
                                eduranceInt,pizzazzInt );
                        //and finally add the preferences to the list of the juggler
                        for (String circuit: arrayOfPreferences) {
                            //we only care about the ints!
                            String convertToInt = circuit.replace("C", "");
                            j.addCircuitToPreferenceList(circs.get(Integer.parseInt(convertToInt)));
                        }
                        //finally, add the juggler to the list
                        jugglers.add(j);

                    }

                }


            }

        } catch (IOException e) {
            System.out.println("An error has occurred. Exiting...");
            System.exit(1);
        }

        //Set the max members for a circuit.
        setMaxNumberOfCircuits();

    }

    /**
     * return the Circuit list to pass it to FormTeam.java
     * @return ArrayList of Circuits
     */
    public ArrayList <Circuit> getCircs (){
        return this.circs;
    }

    /**
     * return the Juggler list to pass it to FormTeam.java
     * @return ArrayList of Jugglers
     */
    public ArrayList<Juggler> getJugglers(){
        return this.jugglers;
    }

    /**
     * set the maximum number of Jugglers in each circuit.
     * Formula given on the puzzle description which states # of juggler/ # of circuits = members per circuit
     */
    public void setMaxNumberOfCircuits(){
        int sizeOfJugglers = jugglers.size();
        int sizeOfCircuits = circs.size();
        for(Circuit c : circs){
            c.setMaxMembers(sizeOfJugglers/sizeOfCircuits);
        }
    }
}
