package cn.scorestatistics.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Classmanager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String classname;

    private String total;

    private int grade;

    private String profession;

    private int state;

    private String description;

    private Date created;

    private Date updated;

    public Classmanager() {

    }

    public Classmanager(long id, String classname, String total, int grade, String profession, String description, int state, Date created, Date updated) {
        this.id = id;
        this.classname = classname;
        this.total = total;
        this.grade = grade;
        this.profession = profession;
        this.description = description;
        this.state = state;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
