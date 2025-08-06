package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // このクラスがDBのテーブルに対応することを示す
public class DemoItem {

    @Id // 主キー（Primary Key）
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主キーの自動採番設定（AUTO_INCREMENT）
    private Long id; // 数値IDで自動採番
    
    @ManyToOne
    @JoinColumn(name = "brand_id") //外部キー列の名前（DB上のカラム名）
    private Brand brand;

    private String demoName;
    private int price;
    private int quantity;
    private String comment;
}
