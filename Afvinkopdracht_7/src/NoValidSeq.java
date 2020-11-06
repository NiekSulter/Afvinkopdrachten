public class NoValidSeq extends Exception{
    public NoValidSeq() {

    }

    public NoValidSeq(String message) {
        super (message);
    }

    public NoValidSeq(Throwable cause) {
        super (cause);
    }

    public NoValidSeq(String message, Throwable cause) {
        super(message, cause);
    }
}
