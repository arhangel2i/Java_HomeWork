package Dz_05;

import java.util.LinkedList;

public class CheckMate_plato {
    private LinkedList<int[][]> rollbackList = new LinkedList<>();
    int checkMate_araray[][];
    int checkMate_backup[][];

    public CheckMate_plato(int x, int y)
    {
        checkMate_araray = new int[x][y];
        checkMate_backup = new int[x][y];
    }
    public CheckMate_plato()
    {
        this(8, 8);
    }
    public String showTechnoDeck()
    {
        StringBuilder retStr= new StringBuilder();
        retStr.append(String.format("\n============\nНе форматированный вывод массива\n---------------\nШирина доски - %s, Высота доски - %s, Расставленно королев - %s\n\n", this.getDeckWidth(), this.getDeckHeight(), this.getCountQueens()));
        for (int i=0; i<checkMate_araray.length; i++)        
        {
            for (int i1=0;i1<checkMate_araray[0].length;i1++)
            {
                retStr.append(checkMate_araray[i][i1]);
                retStr.append(" ");
            }
            retStr.append("\n");
        }
        return retStr.toString();
    }
    public String showDeck()
    {
        StringBuilder retStr= new StringBuilder();
        retStr.append(String.format("\n============\nФорматированный вывод массива\n---------------\nШирина доски - %s, Высота доски - %s, Расставленно королев - %s\n\n", this.getDeckWidth(), this.getDeckHeight(), this.getCountQueens()));
        for (int i=0; i<checkMate_araray.length; i++)        
        {
            for (int i1=0;i1<checkMate_araray[0].length;i1++)
            {
                switch (checkMate_araray[i][i1]) {
                    case 2:
                        retStr.append("Q|");
                        break;
                    default:
                        retStr.append("*|");
                        break;
                }

                retStr.append(" ");
            }
            retStr.append("\n");
        }
        return retStr.toString();
    }
    public int getDeckWidth()
    {
        return checkMate_araray.length;
    }
    public int getDeckHeight()
    {
        return checkMate_araray[0].length;
    }
    public int getCountQueens()
    {
        int qCount = 0;
        for (int i=0; i<this.getDeckHeight();i++)
        {
            for (int i1=0; i1<this.getDeckWidth();i1++)
            {
                if (checkMate_araray[i][i1]==2) qCount++;
            }
        }
        return qCount;
    }
    public boolean setQueenPosition(int x,int y)
    {
        if (((x)<=this.getDeckWidth() && (x)>=0) && ((y)<=this.getDeckHeight() && (y)>=0))
        {
            if (checkMate_araray[x][y]==0)
            {
                this.saveCurrentDeck();
                checkMate_araray[x][y] = 2;
                clearHorizontalPosition(x);
                clearVerticalPosition(y);
                clearDiagPosition(x,y);
                return true;
            }
        }
        return false;
    }
    public int setQueenFreeposInRow(int y, int StartPos)
    {
        for (int i=StartPos; i<this.getDeckWidth(); i++)
        {
            if (setQueenPosition(y, i))
            {
                return i;
            }
        }
        return -1;
    }
    public int setQueenFreeposInRow(int y)
    {
        return setQueenFreeposInRow(y, 0);
    }
    public int setQueenNextFreePosInRow(int y)
    {
        int pos = -1;
        for (int i=0; i<this.getDeckWidth(); i++)
        {
            if (checkMate_araray[y][i]==2)
            {
                pos = i;
            }
        }
        if (pos!=-1)
        {
            this.rollBack();
            pos++;
            pos = setQueenFreeposInRow(y, pos);
            if (pos!=-1)
            {
                return pos;
            }
        }
        return -1;
    }
    public void clearDeck()
    {
        for (int i=0; i<this.getDeckWidth();i++)
        {
            for (int i1=0; i1<this.getDeckHeight();i1++)
            {
                checkMate_araray[i][i1]=0;
            }
        }
    }
    private void clearHorizontalPosition(int x)
    {
        for (int i=0; i<getDeckHeight(); i++)
        {
            if (checkMate_araray[x][i]==0)
            {
                checkMate_araray[x][i]=1;
            }
        }
    }
    private void clearVerticalPosition(int y)
    {
        for (int i=0; i<getDeckHeight(); i++)
        {
            if (checkMate_araray[i][y]==0)
            {
                checkMate_araray[i][y]=1;
            }
        }
    }
    private void clearDiagPosition(int x,int y)
    {
        int offset = x>=y?y:x;
        int startXdiag = x-offset;
        int startYdiag = y-offset;
        int maxyOffset = this.getDeckHeight()-startYdiag;
        int maxxOffset = this.getDeckWidth()-startXdiag;
        int cntI = maxyOffset>=maxxOffset?maxxOffset:maxyOffset;
        for (int i=0; i<cntI;i++)
        {
            if (checkMate_araray[startXdiag+i][startYdiag+i]==0)
            {
                checkMate_araray[startXdiag+i][startYdiag+i]=9;                 
            }
        }

        offset = x+y;
        if (offset >(this.getDeckWidth()-1))
        {
            startXdiag = this.getDeckWidth()-1;
            startYdiag = (x+y) - (this.getDeckWidth()-1);
        }
        else
        {
            startXdiag=offset;
            startYdiag=0;
        }
               
        maxyOffset = (this.getDeckHeight()-1)-startYdiag;
        maxxOffset = startXdiag-maxyOffset;
        if (maxxOffset<0)
        {
            maxyOffset +=maxxOffset;
            maxxOffset = 0;
        }
        else
        {
            maxyOffset=(this.getDeckHeight()-1);
        }

        cntI = startXdiag-maxxOffset;
        for (int i=0; i<=cntI;i++)
        {
            if (checkMate_araray[startXdiag-i][startYdiag+i]==0)
            {
                checkMate_araray[startXdiag-i][startYdiag+i]=9;                 
            }
        }

    }
    private int[][] saveStateDeck()
    {
        int tempDeck[][] = new int[this.getDeckHeight()][this.getDeckWidth()];
        for (int i=0; i<this.getDeckHeight();i++)
        {
            for (int i1=0; i1<this.getDeckWidth();i1++)
            {
                tempDeck[i][i1]=checkMate_araray[i][i1];
            }
        }
        return tempDeck;
    }
    private void saveCurrentDeck()
    {
        int [][] deckState= saveStateDeck();
        rollbackList.push(deckState);
    }
    public void rollBack()
    {
        if (!rollbackList.isEmpty())
        {
            checkMate_araray = rollbackList.pop();
        }
    }
}
