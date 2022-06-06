package Dz_02;
public class dz_02 {
    public static void main(String[] args) {
        long x = 3;
        System.out.println(FormatResult(x, GetTriangleValue(x), GetTriangleValueStringVersion(x)));
        x=10;
        System.out.println(FormatResult(x, GetTriangleValue(x), GetTriangleValueStringVersion(x)));
        x=20;
        System.out.println(FormatResult(x, GetTriangleValue(x), GetTriangleValueStringVersion(x)));
        x=100;
        System.out.println(FormatResult(x, GetTriangleValue(x), GetTriangleValueStringVersion(x)));
        x=-1;
        System.out.println(FormatResult(x, GetTriangleValue(x), GetTriangleValueStringVersion(x)));
    }

    public static long GetTriangleValue(long val) {
        long retVal = 0;
        for (long i =1;i<=val;i++)
        {
            retVal += i;
        }
        return retVal;
    }

    public static String GetTriangleValueStringVersion(long val) {
        StringBuilder sb = new StringBuilder();
        if (val>=1)
        {
            sb.append("1");
            for (long i =2;i<=val;i++)
            {
                sb.append("+" + Long.toString(i));
            }
        }
        return sb.toString();
    }

    public static String FormatResult(long val, long result, String sequenceData)
    {
        return String.format("Вычислить треугольное значение числа: %d\nРезультат: %d\nПоследовательность:%s\n---", val, result, sequenceData);
    }
}
