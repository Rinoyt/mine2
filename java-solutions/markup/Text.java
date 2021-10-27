package markup;

public class Text implements  Mark {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(text);
    }
    @Override
    public String toString(){
        return text;
    }
}
