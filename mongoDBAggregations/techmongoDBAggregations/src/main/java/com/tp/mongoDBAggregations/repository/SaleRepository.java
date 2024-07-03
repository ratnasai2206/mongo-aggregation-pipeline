package com.tp.mongoDBAggregations.repository;

import com.tp.mongoDBAggregations.entity.Sale;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleRepository extends MongoRepository<Sale, String> {

    @Aggregation(pipeline = {
            "{ '$group': { '_id': null, 'totalAmount': { '$sum': '$amount' } } }",
            "{ '$project': { '_id': 0, 'totalAmount': 1 } }"
    })
    AggregationResults<?> getTotalSalesAmount();

    @Aggregation(pipeline = {
            "{ '$group': { '_id': null, 'averageAmount': { '$avg': '$amount' } } }",
            "{ '$project': { '_id': 0, 'averageAmount': 1 } }"
    })
    AggregationResults<?> getAverageSalesAmount();

    @Aggregation(pipeline = {
            "{ '$group': { '_id': '$category', 'count': { '$sum': 1 } } }"
//            "{ '$project': { '_id': 1, 'count':1}}"
    })
    AggregationResults<?> getSalesCountByCategory();

    @Aggregation(pipeline = {
            "{ '$group': { '_id': null, 'maxAmount':{ '$max': '$amount' } } }",
            "{ '$project': { '_id': 0,'maxAmount': 1 } }"

    })
    AggregationResults<?> getMaxSaleAmount();

    @Aggregation(pipeline = {
            "{ '$group': { '_id': null, 'minAmount':{ '$min': '$amount' } } }",
            "{ '$project': { '_id': 0,'minAmount': 1 } }"

    })
    AggregationResults<?> getMinSalesAmount();

    @Aggregation(pipeline = {
            "{ '$group': { '_id': '$category'}}"
    })
    AggregationResults<?> getDistinctCategories();

    @Aggregation(pipeline = {
            "{ '$match': { 'category': ?0 } }",
            "{ '$sort': { 'amount': -1 } }",
            "{ '$limit': 3 }",
            "{ '$project': { '_id': 0, 'amount': 1, 'category': 1, 'item': 1 } }"
    })
    AggregationResults<?> getTop3SalesByCategory(String category);

    @Aggregation(pipeline = {
            "{ '$match': { 'category': { '$exists': true } } }",
            "{ '$group': { '_id': '$category', 'totalAmount': { '$sum': '$amount' }, 'averageAmount': { '$avg': '$amount' } } }",
            "{ '$sort': { 'totalAmount': -1 } }",
            "{ '$project': { '_id': 0, 'category': '$_id', 'totalAmount': 1, 'averageAmount': 1 } }"
    })
    AggregationResults<?> getCustomAggregatedSales();
}
