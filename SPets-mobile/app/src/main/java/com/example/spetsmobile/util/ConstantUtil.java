package com.example.spetsmobile.util;

import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.DiseaseResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.ScheduleResponse;
import com.example.spetsmobile.model.response.SymptomResponse;
import com.example.spetsmobile.model.response.VaccineResponse;

import java.util.List;

public class ConstantUtil {

    private static AuthResponse auth = null;
    private static PetResponse petResponse = null;
    private static VaccineResponse vaccineResponse = null;
    private static ScheduleResponse scheduleResponse = null;
    private static HospitalResponse hospitalResponse = null;
    private static BookingResponse bookingResponse = null;
    private static MedicalRecordResponse medicalRecordResponse = null;
    private static SymptomResponse symptomResponse = null;
    private static DiseaseResponse diseaseResponse = null;
    private static List<DiseaseResponse> diseaseResponseList = null;
    private static String MSG = null;

    private static String ACCESS_TOKEN = "";
    private static String REFRESH_TOKEN = "";

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public static String getRefreshToken() {
        return REFRESH_TOKEN;
    }

    public static void setRefreshToken(String refreshToken) {
        REFRESH_TOKEN = refreshToken;
    }

    public static AuthResponse getAuth() {
        return auth;
    }

    public static void setAuth(AuthResponse auth) {
        ConstantUtil.auth = auth;
    }

    public static PetResponse getPetResponse() {
        return petResponse;
    }

    public static void setPetResponse(PetResponse petResponse) {
        ConstantUtil.petResponse = petResponse;
    }

    public static VaccineResponse getVaccineResponse() {
        return vaccineResponse;
    }

    public static void setVaccineResponse(VaccineResponse vaccineResponse) {
        ConstantUtil.vaccineResponse = vaccineResponse;
    }

    public static ScheduleResponse getScheduleResponse() {
        return scheduleResponse;
    }

    public static void setScheduleResponse(ScheduleResponse scheduleResponse) {
        ConstantUtil.scheduleResponse = scheduleResponse;
    }

    public static HospitalResponse getHospitalResponse() {
        return hospitalResponse;
    }

    public static void setHospitalResponse(HospitalResponse hospitalResponse) {
        ConstantUtil.hospitalResponse = hospitalResponse;
    }

    public static MedicalRecordResponse getMedicalRecordResponse() {
        return medicalRecordResponse;
    }

    public static void setMedicalRecordResponse(MedicalRecordResponse medicalRecordResponse) {
        ConstantUtil.medicalRecordResponse = medicalRecordResponse;
    }

    public static SymptomResponse getSymptomResponse() {
        return symptomResponse;
    }

    public static void setSymptomResponse(SymptomResponse symptomResponse) {
        ConstantUtil.symptomResponse = symptomResponse;
    }

    public static DiseaseResponse getDiseaseResponse() {
        return diseaseResponse;
    }

    public static void setDiseaseResponse(DiseaseResponse diseaseResponse) {
        ConstantUtil.diseaseResponse = diseaseResponse;
    }

    public static List<DiseaseResponse> getDiseaseResponseList() {
        return diseaseResponseList;
    }

    public static void setDiseaseResponseList(List<DiseaseResponse> diseaseResponseList) {
        ConstantUtil.diseaseResponseList = diseaseResponseList;
    }

    public static String getMSG() {
        return MSG;
    }

    public static BookingResponse getBookingResponse() {
        return bookingResponse;
    }

    public static void setBookingResponse(BookingResponse bookingResponse) {
        ConstantUtil.bookingResponse = bookingResponse;
    }

    public static void setMSG(String MSG) {
        ConstantUtil.MSG = MSG;
    }
}
