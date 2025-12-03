package com.example.familytree.dto;

import java.time.LocalDate;

public class MemberDTO {
    private Long id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private Long fatherId;
    private String fatherName;
    private Long motherId;
    private String motherName;
    private Long spouseId;      // ✅ new field
    private String spouseName;  // ✅ new field

    public MemberDTO() {}

    public MemberDTO(Long id, String name, String gender, LocalDate dateOfBirth,
                     Long fatherId, String fatherName,
                     Long motherId, String motherName,
                     Long spouseId, String spouseName) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.fatherId = fatherId;
        this.fatherName = fatherName;
        this.motherId = motherId;
        this.motherName = motherName;
        this.spouseId = spouseId;
        this.spouseName = spouseName;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Long getFatherId() { return fatherId; }
    public void setFatherId(Long fatherId) { this.fatherId = fatherId; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public Long getMotherId() { return motherId; }
    public void setMotherId(Long motherId) { this.motherId = motherId; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public Long getSpouseId() { return spouseId; }
    public void setSpouseId(Long spouseId) { this.spouseId = spouseId; }

    public String getSpouseName() { return spouseName; }
    public void setSpouseName(String spouseName) { this.spouseName = spouseName; }
}