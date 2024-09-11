package com.Emazon.stock_service.Utils;

public class Constant {
    public static final int MAX_CATEGORY_NAME_LENGTH = 50;
    public static final int MAX_CATEGORY_DESCRIPTION_LENGTH = 90;

    public static final int MAX_BRAND_NAME_LENGTH = 50;
    public static final int MAX_BRAND_DESCRIPTION_LENGTH = 120;

    public static final int MAX_PRODUCT_NAME_LENGTH = 50;
    public static final int MAX_PRODUCT_DESCRIPTION_LENGTH = 120;
    public static final int MAX_CATEGORIES_PER_PRODUCT = 3;

    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE =" 10";
    public static final String SORT_DIRECTION_ASC = "ASC";
    public static final String SORT_DIRECTION_DESC = "DESC";

    public static final String CATEGORY_NO_NULL = "Category can't be null";
    public static final String INVALID_CATEGORY_NAME_LENGTH = "Category name must have maximum "+ Constant.MAX_CATEGORY_NAME_LENGTH + "characters";
    public static final String INVALID_CATEGORY_DESCRIPTION_LENGTH = "Category description must have maximum "+ Constant.MAX_CATEGORY_DESCRIPTION_LENGTH + "characters";
    public static final String CATEGORY_MUST_HAVE_NAME = "The category must have a name";
    public static final String CATEGORY_MUST_HAVE_DESCRIPTION = "The category must have a description";

    public static final String BRAND_NO_NULL = "Brand can't be null";
    public static final String INVALID_BRAND_NAME_LENGTH = "Brand name must have maximum "+ Constant.MAX_BRAND_NAME_LENGTH + "characters";
    public static final String INVALID_BRAND_DESCRIPTION_LENGTH = "Brand description must have maximum "+ Constant.MAX_BRAND_DESCRIPTION_LENGTH + "characters";
    public static final String BRAND_MUST_HAVE_NAME = "The brand must have a name";
    public static final String BRAND_MUST_HAVE_DESCRIPTION = "The brand must have a description";

    public static final String PRODUCT_NO_NULL = "Product can't be null";
    public static final String INVALID_PRODUCT_NAME_LENGTH = "Product name cannot be longer than " + Constant.MAX_PRODUCT_NAME_LENGTH + " characters";
    public static final String INVALID_PRODUCT_DESCRIPTION_LENGTH ="Product description cannot be longer than " + Constant.MAX_PRODUCT_DESCRIPTION_LENGTH + " characters";
    public static final String PRODUCT_MUST_HAVE_NAME = "The product must have a name";
    public static final String PRODUCT_MUST_HAVE_DESCRIPTION = "The product must have a description";
    public static final String PRODUCT_MUST_HAVE_PRICE = "The product must have a price";
    public static final String PRODUCT_MUST_HAVE_QUANTITY = "The product must have a quantity";
}
