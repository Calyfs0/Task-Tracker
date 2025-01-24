public enum Status {
    DONE ("done"),
    INPROGRESS ("in-progress"),
    TODO ("todo");

    private final String value;
    
    Status(String value) {
        this.value = value;    
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}
