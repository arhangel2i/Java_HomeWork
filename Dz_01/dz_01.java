package Dz_01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public class dz_01 {
    public static void main(String[] args) {
        System.out.println("Пример 1: а = 3, b = 2, ответ: " + sqrt(3, 2));
        System.out.println("Пример 2: а = 2, b = -3, ответ: " + sqrt(2, -3));
        System.out.println("Пример 3: а = 3, b = 0, ответ: " + sqrt(3, 0));
        System.out.println("Пример 4: а = 0, b = 0, ответ: " + sqrt(0, 0));

        double a=0;double b = 0;
        System.out.println("Пример 5: Cчитывание данных из файла");
        try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
            String str;
            while((str = br.readLine())!=null)
            {
                System.out.println(str);
                String fChar = str.substring(0,1);
                switch (fChar) {
                    case "a":
                        a=Double.parseDouble(str.substring(2));
                        break;
                    case "b":
                        b=Double.parseDouble(str.substring(2));
                        break;
                }
            }
            br.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String retRes = sqrt(a, b);
        System.out.println("Запись вычислений в файл");
        System.out.println(retRes);
        WriteResult(retRes, "output.txt");
    }
    public static String sqrt(double a, double b)
    {
        DecimalFormat dF = new DecimalFormat("#.######");
        String retRes = a==0?"не определено":dF.format(Math.pow(a, b));;
        return retRes;
    }
    public static void WriteResult(String retRes, String FileName)
    {
        try(FileWriter fw = new FileWriter(FileName, false)){
            fw.write(retRes);
            fw.flush();
            fw.close();
        } catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}