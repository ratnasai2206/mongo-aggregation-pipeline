package com.tp.mongoDBAggregations.service;

import com.tp.mongoDBAggregations.entity.Sale;
import com.tp.mongoDBAggregations.repository.SaleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class SalesPipelineService {

   private final SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public List<?> getTotalSalesAmount(){
        return saleRepository.getTotalSalesAmount().getMappedResults();
    }


    public List<?> getAverageSalesAmount(){
        return saleRepository.getAverageSalesAmount().getMappedResults();
    }

    public List<?> getSalesCountByCategory(){
        return saleRepository.getSalesCountByCategory().getMappedResults() ;
    }

    public List<?> getMaxSaleAmount() {
        return saleRepository.getMaxSaleAmount().getMappedResults();
    }

    public List<?> getMinSalesAmount() {
        return saleRepository.getMinSalesAmount().getMappedResults();
    }

    public List<?> getDistinctCategories(){
        return saleRepository.getDistinctCategories().getMappedResults();
    }

    public List<?> getTop3SalesByCategory(String category){
        return saleRepository.getTop3SalesByCategory(category).getMappedResults();
    }

    public List<?> getCustomAggregatedSales(){
        return saleRepository.getCustomAggregatedSales().getMappedResults();
    }
}
