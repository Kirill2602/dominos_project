package ru.dominospizza.tests.api.models.addproductincart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    private String productCode;
    private String osgCode;
    private String sizeCode;
    private String doughCode;
    private String sideCode;
    private String categoryCode;
    private int quantity;
    private ArrayList<Object> modifiers;
    private boolean excludeCampaign;
}
