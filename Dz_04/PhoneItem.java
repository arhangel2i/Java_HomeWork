package Dz_04;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Элемент телефонной книги включающий
 */
public class PhoneItem
{
    private BasePhoneItemName phoneName;
    private String phoneNumber;

    public PhoneItem(BasePhoneItemName phoneName, String phoneNumber)
    {
        this.phoneName=phoneName;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s: %s. Телефон: %s", phoneName.getPhoneType(), this.phoneName, this.phoneNumber);
    }

    public String toShortString()
    {
        return String.format("%s %s", phoneName.getShortName(), phoneNumber);
    }

    public String saveFormatLong()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(phoneName.toSaveFormatLong());
        sb.append(phoneNumber + "\n");
        return sb.toString();
    }

    public String saveFormatShort()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(phoneName.toSaveFormatShort());
        sb.append(phoneNumber + "\n");
        return sb.toString();
    }

    protected static PhoneItem LoadItemFromString(String Data)
    {
        Scanner scanner = new Scanner(Data);
        if (scanner.hasNextLine())
        {
            String str = scanner.nextLine();
            String recType = getRecordType(str);
            try
            {
                Class<?> myClass = Class.forName(recType);
                Method loadItem = myClass.getMethod("LoadItemFromStringLong", String.class);
                StringBuilder parameters = new StringBuilder();
                while(true)
                {
                    str = scanner.nextLine();
                    if (!scanner.hasNextLine()) break;
                    else parameters.append(str + "\n");
                }
                
                BasePhoneItemName bpin= (BasePhoneItemName) loadItem.invoke(myClass, parameters.toString());
                return new PhoneItem(bpin, str);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    private static String getRecordType(String s)
    {
        return s.replace("#", "");
    }
}