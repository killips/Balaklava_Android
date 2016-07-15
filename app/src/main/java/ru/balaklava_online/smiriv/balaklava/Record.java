package ru.balaklava_online.smiriv.balaklava;

/**
 * Created by smiriv on 14.07.2016.
 */
public class Record {

    public enum Type {RED, GREEN, YELLOW}

    private String name;
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Record copy(){
        Record copy = new Record();
        copy.setType(type);
        copy.setName(name);
        return copy;
    }
}