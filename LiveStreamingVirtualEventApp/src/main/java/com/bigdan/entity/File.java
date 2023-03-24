package com.bigdan.entity;

import javax.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;

    @Column(length = 30000000)
    private byte[] fileByte;

    public File( String name, String type, byte[] picByte) {

        this.name = name;
        this.type = type;
        this.fileByte = picByte;
    }

    public File() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return fileByte;
    }

    public void setPicByte(byte[] picByte) {
        this.fileByte = picByte;
    }
}
