package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Brand;
import com.example.demo.entity.DemoItem;
import com.example.demo.repositories.BrandRepository;
import com.example.demo.repositories.DemoItemRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j // ログ出力
public class ItemListController {

    @Autowired
    private DemoItemRepository demoItemRepository;

    @Autowired
    private BrandRepository brandRepository;

    // ========================================
    // 一覧表示
    // ========================================
    @GetMapping("/demo_list")
    public String showList(
            @RequestParam(required = false) Long brand, // ブランド
            @RequestParam(required = false) String keyword, // キーワード
            Model model) {

        log.info("=== 一覧表示開始 ===");
        log.info("検索条件 brand: {}, keyword: {}", brand, keyword);

        // ブランドを取得（プルダウン表示）
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("brands", brands);

        // 検索条件に応じた抽出処理
        List<DemoItem> demoItems;

        if ((brand == null || brand == 0) && (keyword == null || keyword.isBlank())) {
            demoItems = demoItemRepository.findAll(); // 検索条件なし：全件表示
        } else {
            demoItems = demoItemRepository.searchByConditions(brand, keyword); // 条件付き検索
        }

        log.info("取得件数: {}", demoItems.size());
        for (DemoItem item : demoItems) {
            log.info("Item: id={}, name={}, brand={}, price={}",
                    item.getId(),
                    item.getDemoName(),
                    item.getBrand() != null ? item.getBrand().getBrandName() : "null",
                    item.getPrice());
        }

        model.addAttribute("demoItems", demoItems);

        // 検索条件の保持（再表示）
        model.addAttribute("selectedBrandId", brand);
        model.addAttribute("keyword", keyword);

        return "demo_list"; // demo_list.html は DBの内容を使って表示するように
    }

    // ========================================
    // 編集フォーム表示（修正）
    // ========================================
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        DemoItem item = demoItemRepository.findById(id).orElse(null);
        List<Brand> brands = brandRepository.findAll();

        model.addAttribute("id", item);
        model.addAttribute("brands", brands);
        return "edit";
    }

    // 編集内容の保存・更新
    @PostMapping("/edit")
    public String updateItem(@ModelAttribute DemoItem demoItem) {
        demoItemRepository.save(demoItem);
        return "redirect:/demo_list";
    }
}
