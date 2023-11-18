package com.smsys.system.model;

public class FirstLvDisciplineDetail {
    private Long id;
    private String firstLvDisciplineNo;
    private String firstLvDisciplineName;
    public Long getId() {
        return id;
    }
    @Override
    public String toString(){
        return "FirstLvDiscipline{" +
                "id = " + id +
                ", firstLvDisciplineNo = " + firstLvDisciplineNo +
                ", firstLvDisciplineName = " + firstLvDisciplineName +
                "}";
    }
}
