package com.example.familytree.dto;

import java.time.LocalDate;

public class MemberCreateUpdateDTO {
    private String name;
    private String gender;
    private LocalDate dateOfBirth;
    private Long fatherId; // optional
    private Long motherId; // optional
    private Long spouseId; // optional ✅ new field

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Long getFatherId() { return fatherId; }
    public void setFatherId(Long fatherId) { this.fatherId = fatherId; }

    public Long getMotherId() { return motherId; }
    public void setMotherId(Long motherId) { this.motherId = motherId; }

    public Long getSpouseId() { return spouseId; }
    public void setSpouseId(Long spouseId) { this.spouseId = spouseId; }
}