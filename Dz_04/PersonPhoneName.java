package Dz_04;

import java.util.Scanner;

public class PersonPhoneName extends BasePhoneItemName {
    private String Family,FirstName,LastName;
    /**
     * Создание элемента телефонной книги - ФИО
     * @param Family - Фамилия
     * @param FirstName - Имя
     * @param LastName - Отчество
     */
    public PersonPhoneName(String Family, String FirstName, String LastName) {
        this.Family= Family;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.Family, this.FirstName,this.LastName);
    }

    @Override
    public String getPhoneType() {
        return "ФИО";
    }

    @Override
    public String getShortName() {
        return String.format("%s %s.%s.", this.Family, this.FirstName.substring(0, 1),this.FirstName.substring(0, 1));
    }

    @Override
    public String toSaveFormatLong() {
        return String.format("#%s\n%s\n%s\n%s\n", this.getClass().getName(),this.Family,this.FirstName,this.LastName);
    }
    public static PersonPhoneName LoadItemFromStringLong(String strData)
    {
        String item1="",item2="",item3="";
        Scanner scanner = new Scanner(strData);
        if (scanner.hasNextLine())
        {
            item1 = scanner.nextLine();
            item2 = scanner.nextLine();
            item3 = scanner.nextLine();
        }
        scanner.close();
        return new PersonPhoneName(item1, item2, item3);
    }

    @Override
    public String toSaveFormatShort() {
        return String.format("#%s\n%s\n%s\n%s\n", this.getClass().getName(),this.Family,this.FirstName,this.LastName);
    }
}
