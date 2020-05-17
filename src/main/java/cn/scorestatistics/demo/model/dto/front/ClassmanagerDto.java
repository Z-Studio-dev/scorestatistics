package cn.scorestatistics.demo.model.dto.front;

import java.io.Serializable;

public class ClassmanagerDto implements Serializable {

    private long id;

    private String className;

    private int grade;

    private String profession;

    private String description;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getClassName() { return className; }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
