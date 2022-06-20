package Dz_03;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dz_03 {
    //+Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69
    //Требуется восстановить выражение до верного равенства. Предложить хотя бы одно решение или сообщить, что его нет.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение q+w=e где q,w,e>=0: ");
        String inpString = in.next(".+");
        in.close();
        System.out.print("\n----------------------\n");
        try{
            String[] parseVal = GetDataFromString(inpString);
            if (parseVal[0].indexOf("?")==-1 && parseVal[1].indexOf("?")==-1)
            {
                System.out.printf("Ни в одном из значений ('%s', '%s') нет замен знаком вопроса. Нечего вычислять", parseVal[0],parseVal[1]);
            }
            else
            {
                boolean resultFind = false;
                int resVal = Integer.parseInt(parseVal[2]);
                int[][] qPosinpVal1 = GetQPos(parseVal[0]);
                int[][] qPosinpVal2 = GetQPos(parseVal[1]);
                int mininpVal1=CreateValFromString(setMinVal(qPosinpVal1), parseVal[0]); 
                int mininpVal2 = CreateValFromString(setMinVal(qPosinpVal2), parseVal[1]);
                int maxinpVal1=CreateValFromString(setMaxVal(qPosinpVal1), parseVal[0]); 
                int maxinpVal2 = CreateValFromString(setMaxVal(qPosinpVal2), parseVal[1]);
                if (mininpVal1+mininpVal2>resVal || maxinpVal1+maxinpVal2<resVal)
                {
                    System.out.println("Результат=>Решение не найдено");
                }
                else
                {
                    setMinVal(qPosinpVal1);setMinVal(qPosinpVal2);
                    do
                    {
                        int inpVal1 = CreateValFromString(qPosinpVal1, parseVal[0]);
                        do
                        {
                            int inpVal2 = CreateValFromString(qPosinpVal2, parseVal[1]);
                            if (inpVal1 + inpVal2==resVal)
                            {
                                System.out.printf("Результат найден=> %s + %s = %s\n", inpVal1, inpVal2, resVal);
                                resultFind=true;
                            }
                            setNextVal(qPosinpVal2);
                        } while(!isMaxVal(qPosinpVal2) && !resultFind);
                        setNextVal(qPosinpVal1);setMinVal(qPosinpVal2);
                    } while (!isMaxVal(qPosinpVal1) && !resultFind);
                }

            }
        }
        catch(Error err)
        {
            System.out.println(err.getMessage());
        }
        catch(Exception err)
        {
            System.out.printf("Произошла не определенная ошибка => %s\n" + err.getMessage());
        }
    }

    
    static int CreateValFromString(int[][] qPos, String Shabl)
    {
        StringBuilder sb = new StringBuilder(Shabl);
        if (qPos.length>0)
        {
            for (int i =0; i<qPos.length; i++)
            {
                sb.setCharAt(qPos[i][0], (char) (qPos[i][1]+'0')); 
            }
        }
        return Integer.parseInt(sb.toString());
    }
    static boolean isMaxVal(int[][] qPos)
    {
        boolean retBool = true;
        if (qPos.length>0)
        {
            for (int i=0; i<qPos.length; i++){
                if (qPos[i][1]!=9)
                {
                    retBool = false;
                    break;
                }

            }
        }
        return retBool;
    }
    static int[][] setNextVal(int[][] qPos)
    {
        if (qPos.length>0 && !isMaxVal(qPos))
        {
            int val = 0;
            for (int i=qPos.length-1; i>=0; i--){
                val=qPos[i][1]+1;
                if (val==10 && i!=0)
                    qPos[i][1]=0;
                else
                {
                    qPos[i][1]=val;
                    break;
                }
            }
        }
        return qPos;
    }
    static int[][] setMaxVal(int[][] qPos)
    {
        if (qPos.length>0)
        {
            for (int i=0; i<qPos.length; i++){
                qPos[i][1]=9;
            }
        }
        return qPos;
    }
    static int[][] setMinVal(int[][] qPos)
    {
        if (qPos.length>0)
        {
            for (int i=0; i<qPos.length; i++){
                qPos[i][1]=0;
            }
        }
        return qPos;
    }
    static int[][] GetQPos(String val)
    {
        int pos = 0; int countItem =0;
        while(pos!=-1)
        {
            pos =val.indexOf("?", pos);
            if (pos!=-1)
            {
                countItem = countItem+1;pos++;
            }
        }
        int[][] retVal = new int[countItem][2];
        pos = 0;countItem=-1;
        while(pos!=-1)
        {
            pos =val.indexOf("?", pos);
            if (pos!=-1)
            {
                countItem = countItem+1;
                retVal[countItem][0]=pos;retVal[countItem][1]=0;
                pos++;
            }
            else pos=-1;
        }

        return retVal;
    }
    static String[] GetDataFromString(String strdata)
    {
        String[] retStr = new String[3];
        Pattern pattern = Pattern.compile("^([0-9\\?]+)\\+([0-9\\?]+)=([0-9]+)$");
        Matcher matcher= pattern.matcher(strdata);
        if(matcher.find())
        {
            retStr[0]= matcher.group(1);
            retStr[1]= matcher.group(2);
            retStr[2]= matcher.group(3);
        }
        else
        {
            throw new Error("Внимание! Введеное выражение не соответствует шаблону q+w=e");
        }
        return retStr;
    }
}
