import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntList {
    private final List<int[]> appear;
    private final List<Integer> appearLens;

    public IntList() {
        appear = new ArrayList<>();
        appearLens = new ArrayList<>();
    }

    private static int[] addToArr(int n, int[] arr, int num) {
        if (n >= arr.length){
            int[] newArr = new int[n*2];

            System.arraycopy(arr, 0, newArr, 0, n);
            newArr[n] = num;
            return newArr;
        }else{
            arr[n] = num;
            return arr;
        }
    }

    public void add(int pos, int row) {
        appear.add(new int[]{pos, row});
        appearLens.add(2);
    }
    public void addPos(int ind, int pos) {
        appear.set(ind, addToArr(appearLens.get(ind), appear.get(ind), pos));
        appearLens.set(ind, appearLens.get(ind) + 1);
    }
    public void print(int i, BufferedWriter out) throws IOException {
        out.write((appearLens.get(i)+1)/2 + " ");
        for (int j = 0; j < appearLens.get(i); j += 2) {
            out.write(Integer.toString(appear.get(i)[j+1]) + ":" + Integer.toString(appear.get(i)[j]));

            if (j != appearLens.get(i)-2) {
                out.write(" ");
            }
        }
    }
}
