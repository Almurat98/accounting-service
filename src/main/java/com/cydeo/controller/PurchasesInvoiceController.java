package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchaseInvoices")
public class PurchasesInvoiceController {

    @GetMapping("/list")
    public String listPurchaseInvoices(Model model) {


        return "/invoice/purchase-invoice-list";
    }

    @GetMapping("/update")
    public String updatePurchaseInvoices(Model model) {


        return "/invoice/purchase-invoice-update";
    }

    @GetMapping("/create")
    public String createPurchaseInvoices(Model model) {


        return "/invoice/purchase-invoice-create";
    }

}
