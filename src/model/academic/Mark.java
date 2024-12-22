package model.academic;

import java.io.Serializable;

public class Mark implements Serializable {
    private static final long serialVersionUID = 13L;

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

    public double calculateFinalGrade() {
        double att1 = (firstAttestation != null) ? firstAttestation : 0;
        double att2 = (secondAttestation != null) ? secondAttestation : 0;
        double finalExam1 = (finalExam != null) ? finalExam : 0;

        return att1 * 0.3 + att2 * 0.3 + finalExam1 * 0.4;
    }

    public double gradeToGpa() {
//        double totalMark = calculateFinalGrade();
        if (totalMark >= 95) return 4.0;
        else if (totalMark >= 90) return 3.67;
        else if (totalMark >= 85) return 3.33;
        else if (totalMark >= 80) return 3.0;
        else if (totalMark >= 75) return 2.67;
        else if (totalMark >= 70) return 2.33;
        else if (totalMark >= 65) return 2.0;
        else if (totalMark >= 60) return 1.67;
        else if (totalMark >= 55) return 1.33;
        else if (totalMark >= 50) return 1.0;
        else return 0.0;
    }

    public Double getAtt1() { return firstAttestation; }
    public Double getAtt2() { return secondAttestation; }
    public Double getFinalExam() { return finalExam; }
    public String getStringMark() { return stringMark; }
    public Double getTotalMark() { return totalMark; }
    @Override
    public String toString() {
        return "Mark: " + totalMark + " (" + stringMark + ")";
    }


}