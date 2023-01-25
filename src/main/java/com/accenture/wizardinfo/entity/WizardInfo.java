package com.accenture.wizardinfo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wizard_info")
@Getter
@Setter
public class WizardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @Column(name = "joined_date")
    private Date joinedDate;

    private boolean active=true;

}
