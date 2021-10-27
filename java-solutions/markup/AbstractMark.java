package markup;

import java.util.List;

public abstract class AbstractMark implements Mark {
    protected StringBuilder line = new StringBuilder();

    protected AbstractMark(List<Mark> l, String sym){
        line.append(sym);
        for (Mark mark : l) {
            line.append(mark.toString());
        }
        line.append(sym);
    }
}
