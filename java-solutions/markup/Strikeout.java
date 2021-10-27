package markup;

import java.util.List;

public class Strikeout extends AbstractMark {
    public Strikeout(List<Mark> l) {
        super(l, "~");
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
