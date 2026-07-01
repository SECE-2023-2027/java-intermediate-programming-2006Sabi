package org.example;

public class Student {
    private String name;
    private int[] marks;
    public Student(String name, int[] marks) {
        this.name = name;
        this.marks=marks;
    }
    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double calculateAverage(){
        double sum=0;
        for(int mark:marks){
            sum+=mark;
        }
        sum=sum/marks.length;
        return sum;
    }


}
