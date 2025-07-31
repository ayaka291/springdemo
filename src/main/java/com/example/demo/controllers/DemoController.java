package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Brand;
import com.example.demo.models.Item;

import lombok.extern.slf4j.Slf4j;

@Slf4j //←ログ出力
@Controller
public class DemoController {

    @RequestMapping("/hello")
    public String hello(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/demo_list")
    public String demoList(Model model) {
        // Brand リスト
        List<Brand> brands = new ArrayList<>();
        Brand honda = new Brand("01", "HONDA");
        Brand kawasaki = new Brand("02", "KAWASAKI");
        Brand yamaha = new Brand("03", "YAMAHA");
        Brand suzuki = new Brand("04", "SUZUKI");
        brands.add(honda);
        brands.add(kawasaki);
        brands.add(yamaha);
        brands.add(suzuki);

        // Item リスト
        List<Item> demo = new ArrayList<>();
        demo.add(new Item(1, "GB350", 800, 1, "空冷", 500000, "反映された", honda, 1, LocalDateTime.now(), null));
        demo.add(new Item(2, "AA", 800, 2, "水冷", 1000000, "テスト", kawasaki, 1, LocalDateTime.now(), null));
        demo.add(new Item(3, "Z900RS CAFE", 820, 4, "水冷", 1300000, "音よき", kawasaki, 1, LocalDateTime.now(), null));

        model.addAttribute("brands", brands);
        model.addAttribute("demoList", demo);

        //ログ出力
        log.info("demoList: {}", demo);
        

        return "demo_list";
    }

}
