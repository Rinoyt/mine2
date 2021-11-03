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
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(args[0], StandardCharsets.UTF_8);

            try {
                Map<String, Integer> cnt = new LinkedHashMap<>();
                IntList appear = new IntList();

                while (in.hasNextWord()) {
                    String word = in.nextWord();

                    if (cnt.containsKey(word)) {
                        appear.addPos(cnt.get(word), in.getPos());
                        appear.addPos(cnt.get(word), in.getRow());
                    } else {
                        cnt.put(word, cnt.size());
                        appear.add(in.getPos(), in.getRow());
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