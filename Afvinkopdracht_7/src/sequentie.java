/**
 * Abstract class voor het aanmaken van een sequentie.
 */
public abstract class sequentie {
    // private vars
    protected String seq, header;
    protected int seqLength;

    public sequentie(String seq, String header) throws NoValidSeq {
        setSeq(seq);
        setSeqLength();
        setHeader(header);

    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String seq) throws NoValidSeq {
        this.seq = seq;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return this.header;
    }

    public int getSeqLength() {
        return this.seqLength;
    }

    public void setSeqLength() {
        this.seqLength = this.seq.length();
    }

}
