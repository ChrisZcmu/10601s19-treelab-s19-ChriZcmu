import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class inspect{
     public static void main(String[] args) throws IOException{
        String infile = args[0];
        String outfile = args[1];
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(infile)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile)));
        Map<String,Double> map = new HashMap<String,Double>();
        double count = 0;
        double entropy = 0;
        double error = 0;
        br.readLine();
        String line = "";
        while((line = br.readLine())!=null) {
            bw.write(line+"\n");
            String[] ary = line.split(",");
            String s = ary[ary.length-1];
            if(map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }
            else{
                map.put(s,(double)1);
            }
            count++;
        }
        bw.flush();
        bw.close();
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        double[] possibility =  new double[2];
        int i = 0;
        double max = 0;
        while(it.hasNext()){
            String key = it.next();
            double value = map.get(key);
            max = Math.max(max,value);
            possibility[i] = value/count;
            entropy += -possibility[i]*(Math.log(possibility[i])/Math.log(2));
            i++;
        }
        error = (count-max)/count;
        System.out.println("entropy: "+entropy);
        System.out.println("error: "+error);
    }
}
