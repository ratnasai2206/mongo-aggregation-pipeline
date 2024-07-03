package com.tp.mongoDBAggregations.service;

import com.tp.mongoDBAggregations.entity.Sale;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@Data
@RequiredArgsConstructor
public class SaleService {


    private final MongoTemplate mongoTemplate;

    public List<Sale> getAllSales() {
        return mongoTemplate.findAll(Sale.class);
    }

    public List<?> getTotalSalesAmount() {
        Aggregation aggregation = newAggregation(
                group().sum("amount").as("totalAmount"),
                project("totalAmount").andExclude("_id")
        );

        AggregationResults<?> results = mongoTemplate.aggregate(aggregation, "sales", Object.class);
        return results.getMappedResults();
    }

    public List<?> getAverageSalesAmount() {
        Aggregation aggregation = newAggregation(
                group().avg("amount").as("averageAmount"),
                project("averageAmount").andExclude("_id")
        );

        AggregationResults<?> results = mongoTemplate.aggregate(aggregation, "sales", Object.class);
        return results.getMappedResults();
    }

    public List<?> getSalesCountByCategory() {
        Aggregation aggregation = newAggregation(
                group("category").count().as("totalSales")
        );

        AggregationResults<?> results = mongoTemplate.aggregate(aggregation, "sales", Object.class);
        return results.getMappedResults();
    }

    public List<?> getMaxSaleAmount() {
        Aggregation aggregation = newAggregation(
                group().max("amount").as("maxAmount"),
                project("maxAmount").andExclude("_id")
        );

        AggregationResults<?> results = mongoTemplate.aggregate(aggregation, "sales", Object.class);
        return results.getMappedResults();
    }

    public List<?> getDistinctCategories() {
        AggregationOperation groupOperation = group("category");

        Aggregation aggregation = newAggregation(groupOperation);

        AggregationResults<?> results = mongoTemplate.aggregate(aggregation, "sales", Object.class);
        return results.getMappedResults();
    }

    public List<?> getTop3SalesByCategory(String category) {
        Criteria matchCriteria = Criteria.where("category").is(category);

        AggregationOperation matchOperation = match(matchCriteria);
        AggregationOperation sortOperation = sort(Sort.Direction.DESC, "amount");
        AggregationOperation limitOperation = limit(3);
        AggregationOperation projectOperation = project("amount", "category","item").andExclude("_id");


        Aggregation aggregation = newAggregation(
                matchOperation,
                sortOperation,
                limitOperation,
                projectOperation

        );

        AggregationResults<?> results = mongoTemplate.aggregate(aggregation, "sales",Object.class);
        return results.getMappedResults();
    }


    public List<?> getCustomAggregatedSales() {
        Criteria criteria = Criteria.where("category").exists(true);

        AggregationOperation matchOperation = match(criteria);
        AggregationOperation groupOperation = group("category")
                .sum("amount").as("totalAmount")
                .avg("amount").as("averageAmount");
        AggregationOperation sortOperation = sort(Sort.Direction.DESC, "totalAmount");
        AggregationOperation projectOperation = project("totalAmount", "averageAmount").and("category").previousOperation();

        Aggregation aggregation = newAggregation(
                matchOperation,
                groupOperation,
                sortOperation,
                projectOperation
        );

        AggregationResults results = mongoTemplate.aggregate(aggregation, "sales", Object.class);
        return results.getMappedResults();
    }
}

