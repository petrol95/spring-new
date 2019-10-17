package com.geekbrains.entities;

import javax.persistence.*;

@Entity
@Table(name = "disk_hib")
public class DiskHib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "produced_year")
    private int producedYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProducedYear() {
        return producedYear;
    }

    public void setProducedYear(int producedYear) {
        this.producedYear = producedYear;
    }

    public DiskHib() {
    }

    @Override
    public String toString() {
        return "DiskHib id=" + id + " title=" + title + " produced_year:" + producedYear;
    }
}
