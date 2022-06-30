package Dz_06;
import java.util.LinkedList;
import java.util.Stack;
public class NewConvert {
    private String expression = "";
    public NewConvert(String Expr) {
        this.expression = Expr;
    }
    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^");
    }

    private boolean leftAssoc(String c) {
        return !c.equals("^");
    }

    private int priority(String c) {
        if (c.equals("^"))
            return 3;
        if (c.equals("*"))
            return 2;
        if (c.equals("/"))
            return 2;
        if (c.equals("+"))
            return 1;
        if (c.equals("-"))
            return 1;
        return 0;
    }

    public String infixToPostFix() {
        String expr = this.expression;
        int i[] = new int[1];
        LinkedList<String> S = new LinkedList<String>();
        LinkedList<String> O = new LinkedList<String>();

        String tok;
        while ((tok = nextToken(expr, i)) != "") {
            if (tok.equals("("))
                S.add(tok);
            else if (tok.equals(")")) {
                while (S.size() > 0 && !S.getLast().equals("("))
                    O.add(S.removeLast());
                if (S.size() == 0)
                    return "Mismatched parenthesis";
                S.removeLast();
            } else if (isOperator(tok)) {
                while (S.size() > 0 && isOperator(S.getLast())
                        &&
                        ((leftAssoc(tok) && priority(tok) <= priority(S.getLast()))
                                || (!leftAssoc(tok) && priority(tok) < priority(S.getLast())))

                )
                    O.add(S.removeLast());

                S.add(tok);
            } else {
                O.add(tok);
            }
        }
        while (S.size() > 0) {
            if (!isOperator(S.getLast()))
                return "Mismatched parenthesis";
            O.add(S.removeLast());
        }
        if (O.size() == 0)
            return "Ошибка ввода";

        String s = "";
        while (!O.isEmpty()) {
            if (s.length() > 0)
                s += ' ';
            s += O.remove();
        }
        return s;
    }

    private String nextToken(String expr, int[] i) {
        while (i[0] < expr.length() && expr.charAt(i[0]) == ' ')
            i[0]++;
        if (i[0] == expr.length())
            return "";
        String b = "";
        while (i[0] < expr.length() && expr.charAt(i[0]) != ' ' && expr.charAt(i[0]) != '(' && expr.charAt(i[0]) != ')'
                && !isOperator(Character.toString(expr.charAt(i[0]))))
            b += expr.charAt(i[0]++);
        if (b != "")
            return b;
        return Character.toString(expr.charAt(i[0]++));
    };

    public Double calcInfixVal()
    {
        return calcInfixVal(false);
    }

    public Double calcInfixVal(Boolean showCalcStep)
    {
        String expr = this.infixToPostFix();
        Stack<Double> locvar = new Stack<>();
        int counter = 0;
        int i[] = new int[1];

        for (i[0] = 0; i[0] < expr.length(); i[0]++)
        {
            Character c = expr.charAt(i[0]);
            if (Character.isDigit(c))
            {
                String num = GetNum(expr, i);
                locvar.push(Double.parseDouble(num));
            }
            else if (this.priority(Character.toString(c))!=0)
            {        
                counter++;
                double secVal = locvar.size() > 0 ? locvar.pop() : 0;
                double firVal = locvar.size() > 0 ? locvar.pop() : 0;
                locvar.push(ExecMath(c, firVal, secVal));
                if (showCalcStep)
                    System.out.printf("Шаг %s$ %s %s %s = %s\n", counter, firVal, c, secVal, locvar.peek());
            }
      } 
      return locvar.pop();
    }
    private String GetNum(String expr, int[] pos) {
        String num = "";
        for (; pos[0] < expr.length(); pos[0]++) {
            Character charnum = expr.charAt(pos[0]);
            if (Character.isDigit(charnum))
                num += charnum;
            else {
                pos[0]--;
                break;
            }
        }
        return num;
    }
    private double ExecMath(char op, double f, double s)
    {
        switch (op) {
            case '+':
                return f+s;
            case '-':
                return f-s;
            case '*':
                return f*s;
            case '/':
                return f/s;
            case '^':
                return Math.pow(f, s);
            default:
                return 0;
        }
    };
}
