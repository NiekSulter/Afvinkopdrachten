public abstract class sequentie {
    // private vars
    protected String seq;
    protected int seqLength;
    protected int color;

    public sequentie(String seq) throws NoValidSeq{
        setSeq(seq);
        setSeqLength();
    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String seq) throws NoValidSeq {
        this.seq = seq;
    }

    public int getSeqLength() {
        return this.seqLength;
    }

    public void setSeqLength() {
        this.seqLength = this.seq.length();
    }

    public int getColor() {
        return this.color;
    }

}
