package com.smsys.system.model;

public class SupervisorDetail {
    private Long id;
    private String supervisorNo;
    private String supervisorName;
    private String sex;
    private String belongCollege;
    public Long getId() {
        return id;
    }
    @Override
    public String toString(){
        return "Supervisor{" +
                "id = " + id +
                ", supervisorNo = " + supervisorNo +
                ", supervisorName = " + supervisorName +
                ", sex = " + sex +
                ", belongCollege = " + belongCollege +
                "}";
    }
}
