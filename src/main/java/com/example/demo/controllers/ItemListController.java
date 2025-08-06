package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repositories.DemoItemRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j // ログ出力
@Controller
public class ItemListController {

    @Autowired
    private DemoItemRepository demoItemRepository;

    @GetMapping("/demo_list")
    public String showList(Model model) {
        model.addAttribute("demoItems", demoItemRepository.findAll());
        return "demo_list"; // demo_list.html は DBの内容を使って表示するように
    }
}

