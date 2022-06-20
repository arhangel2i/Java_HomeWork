package Dz_04;
import Dz_04.PhoneBook.SaveFormat;
import Dz_04.PhoneBook.ShowType;
public class dz_04 {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.Add(new PhoneItem(new PersonPhoneName("Иванов", "Иван", "Иванович"), "+79998886677"));
        pb.Add(new PhoneItem(new PersonPhoneName("Владимиров", "Владимир", "Владимирович"), "+79998886678"));
        pb.Add(new PhoneItem(new PersonPhoneName("Казимир", "Бобик", "Рюрикович"), "+76667775522"));
        pb.Add(new PhoneItem(new OrganizationPhoneName("ООО", "Кирпич строй"), "+79998886679"));
        pb.ExportToFile("LongDataFormat.txt",SaveFormat.sfLongFormat);
        pb.ExportToFile("ShortDataFormat.txt",SaveFormat.sfShortFormat);
        PhoneBook pbTestImport = new PhoneBook();
        pbTestImport.ImportFromFile("LongDataFormat.txt");

        PhoneBook pbTestImport1 = new PhoneBook();
        pbTestImport1.ImportFromFile("ShortDataFormat.txt");

        System.out.println("------------main----------");
        System.out.println(pb.ShowItemList(ShowType.stFull));
        System.out.println("------------pbTestImport----------");
        System.out.println(pbTestImport.ShowItemList(ShowType.stFull));
        System.out.println("-----------pbTestImport1-----------");
        System.out.println(pbTestImport1.ShowItemList(ShowType.stFull));
    }
}
