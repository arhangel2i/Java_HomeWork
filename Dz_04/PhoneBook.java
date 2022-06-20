package Dz_04;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class PhoneBook
{
    enum ShowType {stFull,stShort}
    enum SaveFormat {sfLongFormat,sfShortFormat}
    private ArrayList<PhoneItem> phoneList = new ArrayList<PhoneItem>();

    public void Add(PhoneItem pi)
    {
        phoneList.add(pi);
    }
    public ArrayList<PhoneItem> getItems()
    {
        return phoneList;
    }

    public String ShowItemList(ShowType showType)
    {
        StringBuilder sb = new StringBuilder();
        for (PhoneItem pi:phoneList)
        {
            if (showType == ShowType.stFull)
                sb.append(pi.toShortString());
            else
                sb.append(pi);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void ExportToFile(String FileName, SaveFormat sf)
    {
        try(FileWriter fw = new FileWriter(FileName, false)){
            String saveFormat = "$" + sf + "\n";
            if (sf == SaveFormat.sfShortFormat)
            {
                saveFormat = "$SaveFormat-Short";
            }

            fw.write(saveFormat);
            for (PhoneItem pi:phoneList)
            {
                if (sf == SaveFormat.sfShortFormat)
                    fw.write(pi.saveFormatShort());
                else
                    fw.write(pi.saveFormatLong());
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        } catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public void ImportFromFile(String FileName){
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            String str;SaveFormat sf;boolean EndImport=false;
            String zagl = br.readLine();
            sf=SaveFormat.valueOf(zagl.replace("$", ""));
            if (zagl.startsWith("$"))
            {
                sf=SaveFormat.valueOf(zagl.replace("$", ""));
                while(EndImport!=true)
                {
                    str = br.readLine();
                    if (str!=null)
                    {
                        if (str.startsWith("#"))
                        {
                            if (sf ==SaveFormat.sfLongFormat)
                            {
                                StringBuilder dataitem = new StringBuilder();
                                dataitem.append(str+"\n");
                                while ((str = br.readLine())!=null)
                                {
                                    if ("".equals(str))
                                        break;
                                    else
                                        dataitem.append(str+"\n");
                                }
                                PhoneItem phoneItem = PhoneItem.LoadItemFromString(dataitem.toString());
                                if (phoneItem!=null) this.Add(phoneItem);
                            }
                        }
                    }
                    else
                    {
                        EndImport = true;
                    }
                }
            }
            br.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}