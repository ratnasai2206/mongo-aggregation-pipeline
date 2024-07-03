package com.tp.mongoDBAggregations.controller;

import com.tp.mongoDBAggregations.entity.Sale;
import com.tp.mongoDBAggregations.service.SaleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {


    private final SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/totalAmount")
    public List<?> getTotalSalesAmount() {
        return saleService.getTotalSalesAmount();
    }

    @GetMapping("/averageAmount")
    public List<?> getAverageSalesAmount() {
        return saleService.getAverageSalesAmount();
    }

    @GetMapping("/countByCategory")
    public List<?> getSalesCountByCategory() {
        return saleService.getSalesCountByCategory();
    }

    @GetMapping("/maxAmount")
    public List<?> getMaxSaleAmount() {
        return saleService.getMaxSaleAmount();
    }

    @GetMapping("/distinctCategories")
    public List<?> getDistinctCategories() {
        return saleService.getDistinctCategories();
    }

    @GetMapping("/top3/{category}")
    public List<?> getTop3SalesByCategory(@PathVariable String category) {
        return saleService.getTop3SalesByCategory(category);
    }


    @GetMapping("/customAggregated")
    public List<?> getCustomAggregatedSales() {
        return saleService.getCustomAggregatedSales();
    }
}
