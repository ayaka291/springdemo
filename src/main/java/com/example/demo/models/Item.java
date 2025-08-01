package com.example.demo.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * アイテム
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {

    // バイク番号
    private Integer demoNo;
    // バイク名
    private String demoName;
    // シート高
    private Integer seatHeight;
    // シリンダー
    private Integer cylinder;
    // 冷却
    private String cooling;
    // 価格
    private Integer price;
    // コメント
    private String comment;
    // ブラン
    private Brand brand;
    // バージョン
    private Integer version;
    // 登録日時
    private LocalDateTime insDt;
    // 更新日時
    private LocalDateTime updDt;

}
