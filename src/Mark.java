public class Mark {
    private Double totalMark;
    private Double firstAttestation;
    private Double secondAttestation;
    private Double finalExam;
    private String stringMark;

    public Mark(double first, double second, double finalExam) {
        this.firstAttestation = first;
        this.secondAttestation  = second;
        this.finalExam  = finalExam;
        this.totalMark = first + second + finalExam;
        this.stringMark = markToString(this.totalMark);
    }

    public Mark() {
        this.totalMark = null;
        this.firstAttestation = null;
        this.secondAttestation = null;
        this.finalExam = null;
        this.stringMark = null;
    }


    public Mark(double mark, int i) {
        updateMark(mark, i);
    }

    public String markToString(double val) {
        if (val < 0 || val > 100) throw new IllegalArgumentException("Invalid mark");
        else if (val < 50) return "F";
        else if (val < 55) return "D-";
        else if (val < 60) return "D+";
        else if (val < 65) return "C-";
        else if (val < 70) return "C";
        else if (val < 75) return "C+";
        else if (val < 80) return "B-";
        else if (val < 85) return "B";
        else if (val < 90) return "B+";
        else if (val < 95) return "A-";
        else return "A";
    }

    public void updateMark(double mark, int i) {
        switch (i) {
            case 1 -> this.firstAttestation = mark;
            case 2 -> this.secondAttestation = mark;
            case 3 -> this.finalExam = mark;
            default -> throw new IllegalArgumentException("Invalid attestation number");
        }
        this.totalMark = (firstAttestation != null ? firstAttestation : 0) +
                (secondAttestation != null ? secondAttestation : 0) +
                (finalExam != null ? finalExam : 0);
        this.stringMark = markToString(this.totalMark);
    }
}