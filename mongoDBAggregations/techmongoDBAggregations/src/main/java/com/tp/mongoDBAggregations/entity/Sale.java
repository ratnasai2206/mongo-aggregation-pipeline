package com.tp.mongoDBAggregations.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "sales")
@Data
@RequiredArgsConstructor
public class Sale {

    @Id
    private String id;
    private String item;
    private int quantity;
    private double amount;
    private String date;
    private String category;

}
