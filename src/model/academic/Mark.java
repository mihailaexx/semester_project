package model.academic;

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
        recalculate();
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
        if (val < 50) return "F";
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
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Invalid mark");
        }
        switch (i) {
            case 1 -> this.firstAttestation = mark;
            case 2 -> this.secondAttestation = mark;
            case 3 -> this.finalExam = mark;
            default -> throw new IllegalArgumentException("Invalid attestation number");
        }
        recalculate();
    }
    private void recalculate() {
        double f = (firstAttestation == null)?0:firstAttestation;
        double s = (secondAttestation == null)?0:secondAttestation;
        double fe = (finalExam == null)?0:finalExam;
        this.totalMark = f + s + fe;
        this.stringMark = markToString(totalMark);
    }

    public Double getAtt1() { return firstAttestation; }
    public Double getAtt2() { return secondAttestation; }
    public Double getFinalExam() { return finalExam; }
    public String getStringMark() { return stringMark; }

    @Override
    public String toString() {
        return "Mark: " + totalMark + " (" + stringMark + ")";
    }
}