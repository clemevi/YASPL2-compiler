package Visitor;

public class Attributo<T> {
    
    private String key;
    private T value;

    public Attributo(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public Attributo(String key, int value) {
        this.key = key;
        this.value = (T) String.format("%d", value);
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = (T) value;
    }
    
}