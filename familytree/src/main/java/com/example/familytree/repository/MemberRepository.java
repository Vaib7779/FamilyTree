package com.example.familytree.repository;

import com.example.familytree.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // --- Parent lookups ---
    List<Member> findByFather_Id(Long fatherId);
    List<Member> findByMother_Id(Long motherId);

    // --- Spouse lookups ---
    List<Member> findBySpouse_Id(Long spouseId);
}