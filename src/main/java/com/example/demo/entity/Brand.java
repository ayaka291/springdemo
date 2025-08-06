package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動採番（MySQLのAUTO_INCREMENTなど）
    private Long brandId; // 例: "01", "02" など文字列ID
    private String brandName; // 例: "HONDA", "YAMAHA" など

    public Brand() {} //引数なしコンストラクタ

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<DemoItem> demoItems;
}
