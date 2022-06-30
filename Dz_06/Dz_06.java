package Dz_06;
public class Dz_06 {
    public static void main(String[] args) {
        NewConvert conv = new NewConvert("((2+3*5)+8/2)-14*(2+3)");
        System.out.println(conv.infixToPostFix());
        System.out.println(conv.calcInfixVal(true));
    }
}
