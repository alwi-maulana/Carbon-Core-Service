package com.alwi.carbontracker.constant;

public class CommonConstant {

    private CommonConstant() {
    }

    public static class Default {
        public static final String CREATED_BY = "CARBON_USER";
        public static final String SUCCESS = "SUCCESS";
        public static final String FAILED = "FAILED";
        public static final String SUCCESS_SAVE_DATA = "SUCCESS_SAVE_DATA";
        public static final String SUCCESS_SAVE_DATA_ACTIVITY_AND_EMISSION_CALCULATION = "SUCCESS_SAVE_DATA_ACTIVITY_AND_EMISSION_CALCULATION";

    }

    public static class ErrorMessage {
        public static final String FAILED_SAVE_CARBON_ACTIVITY = "FAILED_SAVE_CARBON_ACTIVITY";
        public static final String FAILED_SAVE_EMISSION_RESULT = "FAILED_SAVE_EMISSION_RESULT";
        public static final String FAILED_GET_EMISSION_INFO = "FAILED_GET_EMISSION_INFO";
        public static final String FAILED_GET_DAILY_SUMMARY = "FAILED_GET_DAILY_SUMMARY";
        public static final String FAILED_GET_ALL_SUMMARY = "FAILED_GET_ALL_SUMMARY";
        public static final String ACTIVITY_TYPE_IS_NOT_FOUND = "ACTIVITY_TYPE_IS_NOT_FOUND";
        public static final String ACTIVITY_IS_NOT_FOUND = "ACTIVITY_IS_NOT_FOUND";
        public static final String FAILED_TO_GET_ALL_EMISSION_FACTOR = "FAILED_TO_GET_ALL_EMISSION_FACTOR";

    }

    public static class CalculationType {
        public static final String EVENT = "EVENT";
        public static final String DAILY = "DAILY";
        public static final String WEEKLY = "WEEKLY";
        public static final String MONTHLY = "MONTHLY";
        public static final String ANNUALLY = "ANNUALLY";
    }
}
