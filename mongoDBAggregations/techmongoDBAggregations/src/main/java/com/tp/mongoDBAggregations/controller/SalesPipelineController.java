package com.tp.mongoDBAggregations.controller;

import com.tp.mongoDBAggregations.entity.Sale;
import com.tp.mongoDBAggregations.service.SalesPipelineService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/salesPipeline")
public class SalesPipelineController {

    private final SalesPipelineService service;

    @GetMapping
    public List<Sale> getAllSales() {
        return service.getAllSales();
    }

    @GetMapping("/totalAmount")
    public List<?> getTotalSalesAmount() {
        return service.getTotalSalesAmount();
    }

    @GetMapping("/averageAmount")
    public List<?> getAverageSalesAmount() {
        return service.getAverageSalesAmount();
    }

    @GetMapping("/countByCategory")
    public List<?> getSalesCountByCategory() {
        return service.getSalesCountByCategory();
    }

    @GetMapping("/maxAmount")
    public List<?> getMaxSaleAmount() {
        return service.getMaxSaleAmount();
    }

    @GetMapping("/minAmount")
    public List<?> getMinSaleAmount() {
        return service.getMinSalesAmount();
    }
    @GetMapping("/distinctCategories")
    public List<?> getDistinctCategories() {
        return service.getDistinctCategories();
    }  @GetMapping("/top3/{category}")
    public List<?> getTop3SalesByCategory(@PathVariable String category) {
        return service.getTop3SalesByCategory(category);
    }
    @GetMapping("/customAggregated")
    public List<?> getCustomAggregatedSales() {
        return service.getCustomAggregatedSales();
    }


}
