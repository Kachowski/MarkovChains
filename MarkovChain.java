import java.io.*;
import java.util.*;

public class MarkovChain {
    public static void main(String[] args)throws Exception
    {
        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Give me file name:");

        File file = new File("C:\\Users\\kelly\\Desktop\\" + s.nextLine() + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Hashtable<String, ArrayList<String>> dict = new Hashtable<String, ArrayList<String>>();

        System.out.println("How many words u want:");
        int wordnum = s.nextInt();

        String st;
        String master = "";
        while ((st = br.readLine()) != null)
            master += (" " + st);

        String[] per = master.split(".?;!");
        for(int i = 0; i < per.length; i++){
            String[] temp = per[i].split(" ");
            for(int j = 0; j < temp.length; j++){
                ArrayList<String> altemp = new ArrayList<String>();
                if(!temp[j].contains(".") && !temp[j].contains("?") && !temp[j].contains(";") && !temp[j].contains("!")){
                    if(dict.containsKey(temp[j])){
                        altemp = dict.get(temp[j]);
                        altemp.add(temp[j+1]);
                        dict.replace(temp[j], altemp);
                    }
                    else{
                        altemp.add(temp[j+1]);
                        dict.put(temp[j], altemp);
                    }
                }
            }
        }

        Enumeration enu = dict.keys();

        String keys = "";

        while (enu.hasMoreElements()) {
            keys += enu.nextElement() + " ";
        }
        String[] total = keys.split(" ");

        String currentword = total[r.nextInt(total.length)];
        while(currentword.equals(currentword.toLowerCase())){
            currentword = total[r.nextInt(total.length)];
        }

        String response = currentword;

        for(int i = 0; i < wordnum-1; i++){
            if(!currentword.contains(".") && !currentword.contains("?") && !currentword.contains("!") && !currentword.contains(";")) {
                int tempnum = r.nextInt(dict.get(currentword).size());
                currentword = dict.get(currentword).get(tempnum);
                response += " " + currentword;
            }
            else{
                currentword = total[r.nextInt(total.length)];
                while(currentword.equals(currentword.toLowerCase())){
                    currentword = total[r.nextInt(total.length)];
                }
                response += " " + currentword;
            }
        }

        System.out.println(response);

    }
}
