public class DisciplineFormula {
    private int n_lectures;
    private int n_practices;
    private int n_labs;

    public DisciplineFormula(int n_lectures, int n_practices, int n_labs) {
        this.n_lectures = n_lectures;
        this.n_practices = n_practices;
        this.n_labs = n_labs;
    }

    public int getN_lectures() {
        return n_lectures;
    }

    public int getN_practices() {
        return n_practices;
    }

    public int getN_labs() {
        return n_labs;
    }

    @Override
    public String toString() {
        return String.format("%d/%d/%d", n_lectures, n_practices, n_labs);
    }
}
