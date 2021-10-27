import java.util.LinkedHashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Character;
import java.lang.StringBuilder;
import java.nio.charset.StandardCharsets;

public class WsppPosition {
    public static boolean check(int got) {
        char curChar = (char) got;
        return Character.isLetter(curChar) || Character.getType(curChar) == Character.DASH_PUNCTUATION || curChar == '\'';
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(args[0], StandardCharsets.UTF_8);

            try {
                Scanner curLine;
                int curChar, pos, row = 0;
                Map<String, Integer> cnt = new LinkedHashMap<>();
                IntList appear = new IntList();

                while (in.hasNextLine()) {
                    curLine = new Scanner(in.nextLine());
                    row++;
                    curChar = curLine.read();
                    pos = 1;

                    while (curChar != -1) {
                        if (check(curChar)) {
                            StringBuilder word = new StringBuilder();
                            while (check(curChar)) {
                                word.append(Character.toLowerCase((char) curChar));
                                curChar = curLine.read();
                            }

                            String finWord = word.toString();

                            if (cnt.containsKey(finWord)) {
                                appear.addPos(cnt.get(finWord), pos);
                                appear.addPos(cnt.get(finWord), row);
                            } else {
                                cnt.put(finWord, cnt.size());
                                appear.add(pos, row);
                            }

                            pos += 1;
                        }

                        curChar = curLine.read();
                    }
                }

                //output
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));

                    try {
                        for (Map.Entry<String, Integer> m : cnt.entrySet()) {
                            out.write(m.getKey() + " ");
                            appear.print(m.getValue(), out);
                            out.newLine();
                        }
                    } finally {
                        out.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Failed to read file: " + ex.getMessage());
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file for reading: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Failed to read file: " + ex.getMessage());
        }
    }
} 