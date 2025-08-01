package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // このクラスがDBのテーブルに対応することを示す
@Data
public class User {

    @Id // 主キー（Primary Key）
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主キーの自動採番設定（AUTO_INCREMENT）
    private long id;

    private String demoNo;
    private String demoName;
    private String cooling;
    private String cylinder;
    private int price;
    private String comment;
    // brand は別テーブルにしたい場合は @ManyToOne にしますが、最初は String でも OK
    private String brandName;

}
