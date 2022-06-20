package Dz_04;

import java.util.Scanner;

public class OrganizationPhoneName extends BasePhoneItemName {
    private String formOrg, orgName;
    /**
     * Создание элемента организации
     * @param formOrg - форма организации
     * @param orgName - название
     */
    public OrganizationPhoneName(String formOrg, String orgName) {
        this.formOrg = formOrg;
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return String.format("%s \"%s\"", this.formOrg, this.orgName);
    }

    @Override
    public String getPhoneType() {
        return "Организация";
    }

    @Override
    public String getShortName() {
        return String.format("%s",this.orgName);
    }

    @Override
    public String toSaveFormatLong() {
        return String.format("#%s\n%s\n%s\n", this.getClass().getName(),this.formOrg,this.orgName);
    }

    public static OrganizationPhoneName LoadItemFromStringLong(String strData)
    {
        String item1="",item2="";
        Scanner scanner = new Scanner(strData);
        if (scanner.hasNextLine())
        {
            item1 = scanner.nextLine();
            item2 = scanner.nextLine();
        }
        scanner.close();
        return new OrganizationPhoneName(item1, item2);
    }

    @Override
    public String toSaveFormatShort() {
        return String.format("#%s@%s@%s", this.getClass().getName(),this.formOrg,this.orgName);
    }
}
