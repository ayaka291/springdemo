package com.example.demo.controllers;

import com.example.demo.entity.Brand;
import com.example.demo.entity.DemoItem;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.DemoItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FormController {

    @Autowired
    private DemoItemRepository demoItemRepository;

    @Autowired
    private BrandRepository brandRepository;

    // フォーム画面の表示
    @GetMapping("/demo_form")
    public String showForm(Model model) {
        // 空のDemoItemオブジェクトを渡す（フォームバインド用）
        model.addAttribute("demoItem", new DemoItem());
        model.addAttribute("brands", brandRepository.findAll());
        return "demo_form";
    }

    // フォーム送信処理（POST）
    @PostMapping("/demo_form")
    public String submitForm(@ModelAttribute DemoItem demoItem, @RequestParam("brandId") Long brandId,
            RedirectAttributes redirectAttributes) {

        Brand brand = brandRepository.findById(brandId).orElse(null);
        demoItem.setBrand(brand);
        demoItemRepository.save(demoItem); // データをDBに保存

        // フラッシュメッセージ
        redirectAttributes.addFlashAttribute("message", "登録完了しました");

        // 登録完了後のリダイレクト先（一覧など）
        return "redirect:/demo_list";
    }
}
