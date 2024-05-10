package com.example.spetsmobile.api;

import com.example.spetsmobile.model.request.AccountResquest;
import com.example.spetsmobile.model.request.BookingResquest;
import com.example.spetsmobile.model.request.HealthRecordResquest;
import com.example.spetsmobile.model.request.HospitalRequest;
import com.example.spetsmobile.model.request.MedicalRecordRequest;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.request.ScheduleResquest;
import com.example.spetsmobile.model.request.SearchRequest;
import com.example.spetsmobile.model.request.VaccineRequest;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.BookingResponse;
import com.example.spetsmobile.model.response.DiseaseResponse;
import com.example.spetsmobile.model.response.HealthRecordResponse;
import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.MediaResponse;
import com.example.spetsmobile.model.response.MedicalRecordResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.ScheduleResponse;
import com.example.spetsmobile.model.response.SymptomResponse;
import com.example.spetsmobile.model.response.VaccineResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    // Đăng nhập hệ thống
    //@FormUrlEncoded
    @POST("/api/auth/login")
    Call<ApiReponse<AuthResponse>> login(@Body AccountResquest accountResquest);

    // Đăng ký tài khoản
    @POST("/api/auth/register")
    Call<ApiReponse<AuthResponse>> register(@Body AccountResquest accountResquest);

    // Quên mật khẩu
    @FormUrlEncoded
    @POST("/api/auth/forgotpassword")
    Call<ApiReponse<AuthResponse>> forgotPassword(@Field("username") String username);

    @GET("/api/pets/owner")
    Call<ApiReponse<List<PetResponse>>> findAllPetsByOwner(@Header("Authorization") String bearerToken, @Query("ownerId") long ownerId);

    @GET("/api/vaccines/owner")
    Call<ApiReponse<List<VaccineResponse>>> findAllVaccinesByOwner(@Header("Authorization") String bearerToken, @Query("ownerId") long ownerId);

    @GET("/api/vaccines/pet")
    Call<ApiReponse<List<VaccineResponse>>> findAllVaccinesByPet(@Header("Authorization") String bearerToken, @Query("petId") long petId);

    @POST("/api/vaccines/save")
    Call<ApiReponse<VaccineResponse>> saveVaccine(@Header("Authorization") String bearerToken, @Body VaccineRequest vaccineRequest);

    @POST("/api/pets/save")
    Call<ApiReponse<PetResponse>> petSave(@Header("Authorization") String bearerToken, @Body PetRequest petRequest);

    @Multipart
    @POST("/api/pets/save/photo/{petId}")
    Call<ApiReponse<PetResponse>> petSavePhoto(@Header("Authorization") String bearerToken, @Path("petId") long petId, @Part MultipartBody.Part avatarMul);

    @GET("/api/health/record/pet")
    Call<ApiReponse<List<HealthRecordResponse>>> findAllHealthRecordByPet(@Header("Authorization") String bearerToken, @Query("petId") long petId);

    @POST("/api/health/record/save")
    Call<ApiReponse<HealthRecordResponse>> saveHealthRecord(@Header("Authorization") String bearerToken, @Body HealthRecordResquest healthRecordResquest);

    @GET("/api/schedule/pet")
    Call<ApiReponse<List<ScheduleResponse>>> findAllScheduleByPet(@Header("Authorization") String bearerToken, @Query("petId") long petId);

    @GET("/api/schedule/account")
    Call<ApiReponse<List<ScheduleResponse>>> findAllScheduleByAccount(@Header("Authorization") String bearerToken, @Query("accountId") long accountId);

    @POST("/api/schedule/save")
    Call<ApiReponse<ScheduleResponse>> scheduleSave(@Header("Authorization") String bearerToken, @Body ScheduleResquest scheduleResquest);

    @POST("/api/schedule/delete")
    Call<ApiReponse<ScheduleResponse>> scheduleDelete(@Header("Authorization") String bearerToken, @Query("scheduleId") long scheduleId);

    @GET("/api/hospitals/list")
    Call<ApiReponse<List<HospitalResponse>>> findAllHospital(@Header("Authorization") String bearerToken);

    @GET("/api/hospitals/account")
    Call<ApiReponse<HospitalResponse>> findByAccount(@Header("Authorization") String bearerToken, @Query("accountId") long accountId);

    @POST("/api/hospitals/save")
    Call<ApiReponse<HospitalResponse>> saveHospital(@Header("Authorization") String bearerToken, @Body HospitalRequest hospitalRequest);

    @GET("/api/media/pet")
    Call<ApiReponse<List<MediaResponse>>> findAllMediaByPet(@Header("Authorization") String bearerToken, @Query("petId") long petId);

    @Multipart
    @POST("/api/media/save/photo/{petId}")
    Call<ApiReponse<PetResponse>> petSaveMedia(@Header("Authorization") String bearerToken, @Path("petId") long petId, @Part MultipartBody.Part avatarMul);

    @POST("/api/bookings/save")
    Call<ApiReponse<BookingResponse>> bookingSave(@Header("Authorization") String bearerToken, @Body BookingResquest bookingResquest);

    @POST("/api/bookings/status")
    Call<ApiReponse<BookingResponse>> bookingStatus(@Header("Authorization") String bearerToken, @Body BookingResquest bookingResquest);

    @GET("/api/bookings/hospital")
    Call<ApiReponse<List<BookingResponse>>> findAllBookingByHospital(@Header("Authorization") String bearerToken,
          @Query("hospitalId") long hospitalId, @Query("status") String status);

    @GET("/api/medicals/record/pet")
    Call<ApiReponse<List<MedicalRecordResponse>>> findAllMedicalRecordByPet(@Header("Authorization") String bearerToken,
                                                                            @Query("petId") long petId);

    @GET("/api/medicals/record/hospital")
    Call<ApiReponse<List<MedicalRecordResponse>>> findAllMedicalRecordByHospital(@Header("Authorization") String bearerToken,
                                                                     @Query("hospitalId") long hospitalId);

    @POST("/api/medicals/record/save")
    Call<ApiReponse<MedicalRecordResponse>> medicalRecordSave(@Header("Authorization") String bearerToken, @Body MedicalRecordRequest medicalRecordRequest);

    @GET("/api/search/symptom")
    Call<ApiReponse<List<SymptomResponse>>> findAllSymptom(@Header("Authorization") String bearerToken);

    @POST("/api/search/disease")
    Call<ApiReponse<List<DiseaseResponse>>> search(@Header("Authorization") String bearerToken, @Body SearchRequest searchRequest);

    @POST("/api/auth/token")
    Call<ApiReponse<AuthResponse>> saveToken(@Header("Authorization") String token, @Body AccountResquest accountResquest);

}
