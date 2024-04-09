package com.example.projectone.Manage;

public interface Constant {
    String personNameCheck = "^[\\u4e00-\\u9fa5]{2,4}$";
    String personCardCheck = "^[1-9][0-9]{5}(18|19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])[0-9]{3}([0-9]|X)$";
    String personAgeCheck = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
}
