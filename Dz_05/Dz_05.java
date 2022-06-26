package Dz_05;
public class Dz_05 {
    public static void main(String[] args) {
        CheckMate_plato checkMate = new CheckMate_plato();
        for (int i=0; i<checkMate.getDeckWidth(); i++)
        {
            if (checkMate.setQueenFreeposInRow(i)==-1) 
            {
                System.out.printf("Не удалось поставить королеву в ряду %s. Переставляем предыдущие фигуры\n", i);
                CorrectPrevPostition(checkMate, i);
            }
        }
        System.out.println(checkMate.showTechnoDeck());
        System.out.println(checkMate.showDeck());
    }

    public static boolean CorrectPrevPostition(CheckMate_plato check, int row)
    {
        while(true)
        {
            if (row==0) break;
            int i = check.setQueenNextFreePosInRow(row-1);
            if (i==-1)
            {
                if (!CorrectPrevPostition(check, row-1))
                {
                    return false;
                }
            }

            if (check.setQueenFreeposInRow(row)!=-1)
            {
                return true;
            }
        }
        return false;
    }
}
