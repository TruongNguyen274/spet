package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "medical_record")
public class MedicalRecord extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "diagnosis")
    private String diagnosis; // Trường này lưu trữ chẩn đoán từ cuộc khám bệnh

    @Column(name = "prescription")
    private String prescription; // Trường này lưu trữ đơn thuốc hoặc hướng dẫn điều trị nếu có

    @Column(name = "notes")
    private String notes; // Trường này lưu trữ ghi chú hoặc mô tả về cuộc khám bệnh và điều trị nếu cần

}
