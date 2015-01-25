import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by cau19 on 15/01/11.
 *
 * JugglerFest accepts arguments and create classes to do a specific job.
 * it also tells that class to dor one or more jobs if needed.
 *
 */
public class JugglerFest {
/*
* Find the best juggler for a circuit to deliver a great show!!*/
    public static void main (String [] args){
        //check if the args length is 1.
        if(args.length !=1){
            System.out.println("You inputted more args necessary to run the program\n" +
                    "Just pass in the file name that has the juggler and circuits data");
            System.exit(1);
        }
        //attempt to create a file. If we can't, end the program. Ask the user if he inputted the wrong file nae
        FileReader file = null;
        try {
            file = new FileReader(args[0]);
        }catch(FileNotFoundException e){
            System.out.println("File was not found.\nDid you input the correct file name?");
            System.exit(1);
        }

        //parse file
        ParseFile parser = new ParseFile(file);
        parser.parseFile();
        //create the team
        FormTeam createTeam = new FormTeam(parser.getCircs(), parser.getJugglers());
        createTeam.addJugglers();

        //Create output file
        try{

        createTeam.printOutput();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found! Exiting the program...");
            System.exit(1);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding! Exiting the program...");
            System.exit(1);
        }


    }
}
