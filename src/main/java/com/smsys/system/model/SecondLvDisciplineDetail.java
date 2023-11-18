package com.smsys.system.model;

public class SecondLvDisciplineDetail {
    private Long id;
    private String secondLvDisciplineNo;
    private String secondLvDisciplineName;
    private String belongFirstLvDisciplineNo;
    public Long getId() {
        return id;
    }
    @Override
    public String toString(){
        return "SecondLvDiscipline{" +
                "id = " + id +
                ", secondLvDisciplineNo = " + secondLvDisciplineNo +
                ", secondLvDisciplineName = " + secondLvDisciplineName +
                ", belongFirstLvDisciplineNo = " + belongFirstLvDisciplineNo +
                "}";
    }
}
