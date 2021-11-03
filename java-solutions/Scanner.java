import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.charset.Charset;
import java.sql.SQLOutput;

public class Scanner {
    private final BufferedReader in;
    private String curLine = null;
    private Integer curInt = null;
    private String curWord = null;
    private int rowCnt = 1;
    private int posCnt = 1;
    private int lastWordRow = 0;
    private int lastWordPos = 0;

    public Scanner(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }
    public Scanner(String in) {
        this.in = new BufferedReader(new StringReader(in));
    }
    public Scanner(String in, Charset encoding) throws IOException {
        this.in = new BufferedReader(new FileReader(in, encoding));
    }

    private static boolean check(int got) {
        char curChar = (char) got;
        return Character.isLetter(curChar) || Character.getType(curChar) == Character.DASH_PUNCTUATION || curChar == '\'';
    }
    private void getNextInt() throws IOException {
        int c = in.read();
        while (c != -1) {
            if (Character.isDigit(c) || (char) c == '-') {
                StringBuilder num = new StringBuilder();
                num.append(Character.toString(c));
                c = in.read();

                while (Character.isDigit(c)) {
                    num.append(Character.toString(c));
                    c = in.read();
                }

                if (!num.toString().equals("-")) {
                    curInt = Integer.parseInt(num.toString());
                    break;
                }
            } else {
                c = in.read();
            }
        }
    }

    public boolean hasNextLine() throws IOException {
        if (curLine != null){
            return true;
        }
        curLine = in.readLine();
        return curLine != null;
    }
    public String nextLine() throws IOException {
        if (curLine != null) {
            String tmp_line = curLine;
            curLine = null;
            return tmp_line;
        }
        return in.readLine();
    }
    public boolean hasNextInt() throws IOException {
        if (curInt != null){
            return true;
        }

        getNextInt();
        return curInt != null;
    }
    public int nextInt() throws IOException {
        if (curInt == null){
            getNextInt();
        }
        int tmpInt = curInt;
        curInt = null;
        return tmpInt;
    }
    public boolean hasNextWord() throws IOException {
        if (curWord != null){
            return true;
        }

        curWord = nextWord();
        return curWord != null;
    }
    public String nextWord() throws IOException {
        if (curWord == null) {
            int c = in.read();
            StringBuilder word = new StringBuilder();
            while (!check(c) && c != -1) {
                if (c == 13){
                    System.out.println((char)c);
                }
                if (System.lineSeparator().contains(Character.toString(c)) && c != 13) {
                    rowCnt++;
                    posCnt = 1;
                }
                c = in.read();
            }
            lastWordPos = posCnt;
            lastWordRow = rowCnt;
            posCnt++;
            while (check(c)) {
                word.append(Character.toLowerCase((char) c));
                c = in.read();
            }
            if (c != -1 && System.lineSeparator().contains(Character.toString(c)) && c != 13) {
                rowCnt++;
                posCnt = 1;
            }

            if (!word.toString().equals("")) {
                return word.toString();
            } else {
                return null;
            }
        }else {
            String stringTmp = curWord;
            curWord = null;
            return stringTmp;
        }
    }
    public int read() throws IOException {
        return in.read();
    }
    public void close() throws IOException {
        in.close();
    }
    public int getRow() {
        return lastWordRow;
    }
    public int getPos() {
        return lastWordPos;
    }
}