package com.practicealone.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="member_table")
@Getter
@Setter
public class Member{

   @Id
    private Long id;
    private String uid;
    private String upw;
    private String uemail;
    private String uname;
    private int age;
}
