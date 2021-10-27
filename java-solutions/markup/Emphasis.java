package markup;

import java.util.List;

public class Emphasis extends AbstractMark {
    public Emphasis(List<Mark> l) {
        super(l, "*");
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(line);
    }
    @Override
    public String toString(){
        return line.toString();
    }
}
