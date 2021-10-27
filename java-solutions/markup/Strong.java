package markup;

import java.util.List;

public class Strong extends AbstractMark {
    public Strong(List<Mark> l) {
        super(l, "__");
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
