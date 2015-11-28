package data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ImportCSV{
    public static ArrayList<String> importCSV(File file){
        ArrayList<String> data = new ArrayList<String>();
        try(BufferedReader input = new BufferedReader(new FileReader(file)) ){
            String temp;
            while((temp = input.readLine())!=null){
                data.add(temp);
            }
        }catch(IOException e){}
        return data;
    }
    
    public static void newCSV(ArrayList<String> data){
        int i;
        File file = new File("newData.csv");
        try(BufferedWriter output = new BufferedWriter(new FileWriter(file))){
            for(i=0;i<data.size();i++){
                output.write(data.get(i));
                output.newLine();
            }
        }catch(IOException e){}
    }
}