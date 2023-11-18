package com.smsys.system.model;

public class StudentDetail {
    private Long id;
    private String studentNo;
    private String studentName;
    private String sex;
    private String belongCollege;
    public Long getId() {
        return id;
    }
    @Override
    public String toString(){
        return "Student{" +
                "id = " + id +
                ", studentNo = " + studentNo +
                ", studentName = " + studentName +
                ", sex = " + sex +
                ", belongCollege = " + belongCollege +
                "}";
    }
}
