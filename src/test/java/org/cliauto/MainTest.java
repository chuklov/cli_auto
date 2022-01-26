package org.cliauto;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        if (args[0].equalsIgnoreCase("-f") && !args[1].isEmpty()) {
            //
            list = readerTest(args[1]);

        }

        reader("./bin/oid.sh ");

    }

    @Test
    public void testOids() {
        List<String> list = new ArrayList<>();
            //
        list = readerTest("oids-master/oids.txt");


        reader("./oids-master/bin/oid.sh ");

        // need to add test conditions
    }

    public static List<String> readerTest(String path) {
        List<String> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private static void reader(String cmd) {
        String s = null;
        try {
            //runs command line
            Runtime run = Runtime.getRuntime();
            Process p = run.exec(cmd);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            OutputStream stdIn = p.getOutputStream ();

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
