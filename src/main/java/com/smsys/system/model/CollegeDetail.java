package com.smsys.system.model;

public class CollegeDetail {
    private Long id;
    private String collegeNo;
    private String collegeName;
    private String user;
    private String auditor;
    public Long getId() {
        return id;
    }
    @Override
    public String toString() {
        return "College{" +
                "id = " + id +
                ", collegeNo = " + collegeNo +
                ", collegeName = " + collegeName +
                ", user = " + user +
                ", auditor = " + auditor +
                "}";
    }
}
