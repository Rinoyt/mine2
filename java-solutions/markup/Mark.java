package markup;

public interface Mark {
    void toMarkdown(StringBuilder s);
    @Override
    String toString();
}
