import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.charset.Charset;

public class Scanner {
    private final BufferedReader in;
    private String curLine = null;
    private Integer curInt = null;

    public Scanner(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }
    public Scanner(String in) {
        this.in = new BufferedReader(new StringReader(in));
    }
    public Scanner(String in, Charset encoding) throws IOException {
        this.in = new BufferedReader(new FileReader(in, encoding));
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
    public int read() throws IOException {
        return in.read();
    }
    public void close() throws IOException {
        in.close();
    }
}