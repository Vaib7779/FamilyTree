package com.example.familytree.service;

import com.example.familytree.dto.MemberCreateUpdateDTO;
import com.example.familytree.dto.MemberDTO;
import com.example.familytree.model.Member;
import com.example.familytree.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {
    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    // --- CRUD ---

    public List<MemberDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MemberDTO findById(Long id) {
        Member m = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Member not found"));
        return toDTO(m);
    }

    public MemberDTO create(MemberCreateUpdateDTO dto) {
        Member m = new Member();
        m.setName(dto.getName());
        m.setGender(dto.getGender());
        m.setDateOfBirth(dto.getDateOfBirth());

        if (dto.getFatherId() != null) {
            Member father = repo.findById(dto.getFatherId())
                    .orElseThrow(() -> new NoSuchElementException("Father not found"));
            m.setFather(father);
        }
        if (dto.getMotherId() != null) {
            Member mother = repo.findById(dto.getMotherId())
                    .orElseThrow(() -> new NoSuchElementException("Mother not found"));
            m.setMother(mother);
        }
        if (dto.getSpouseId() != null) {
            Member spouse = repo.findById(dto.getSpouseId())
                    .orElseThrow(() -> new NoSuchElementException("Spouse not found"));
            m.setSpouse(spouse);
        }

        Member saved = repo.save(m);
        return toDTO(saved);
    }

    public MemberDTO update(Long id, MemberCreateUpdateDTO dto) {
        Member m = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Member not found"));
        m.setName(dto.getName());
        m.setGender(dto.getGender());
        m.setDateOfBirth(dto.getDateOfBirth());

        if (dto.getFatherId() != null) {
            Member father = repo.findById(dto.getFatherId())
                    .orElseThrow(() -> new NoSuchElementException("Father not found"));
            m.setFather(father);
        } else {
            m.setFather(null);
        }

        if (dto.getMotherId() != null) {
            Member mother = repo.findById(dto.getMotherId())
                    .orElseThrow(() -> new NoSuchElementException("Mother not found"));
            m.setMother(mother);
        } else {
            m.setMother(null);
        }

        if (dto.getSpouseId() != null) {
            Member spouse = repo.findById(dto.getSpouseId())
                    .orElseThrow(() -> new NoSuchElementException("Spouse not found"));
            m.setSpouse(spouse);
        } else {
            m.setSpouse(null);
        }

        return toDTO(repo.save(m));
    }

    public void delete(Long id) {
        // Detach children’s parent links
        List<Member> childrenOfFather = repo.findByFather_Id(id);
        for (Member c : childrenOfFather) {
            c.setFather(null);
        }
        List<Member> childrenOfMother = repo.findByMother_Id(id);
        for (Member c : childrenOfMother) {
            c.setMother(null);
        }
        // Detach spouse links
        List<Member> spouses = repo.findBySpouse_Id(id);
        for (Member s : spouses) {
            s.setSpouse(null);
        }
        repo.deleteById(id);
    }

    // --- Relationships ---

    public List<MemberDTO> findChildren(Long id) {
        List<Member> kids = new ArrayList<>();
        kids.addAll(repo.findByFather_Id(id));
        kids.addAll(repo.findByMother_Id(id));
        return kids.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MemberDTO> findSiblings(Long id) {
        Member m = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Member not found"));
        Set<Member> siblings = new HashSet<>();
        if (m.getFather() != null) {
            siblings.addAll(repo.findByFather_Id(m.getFather().getId()));
        }
        if (m.getMother() != null) {
            siblings.addAll(repo.findByMother_Id(m.getMother().getId()));
        }
        siblings.removeIf(s -> Objects.equals(s.getId(), id));
        return siblings.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MemberDTO findSpouse(Long id) {
        Member m = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Member not found"));
        if (m.getSpouse() == null) {
            throw new NoSuchElementException("Spouse not linked");
        }
        return toDTO(m.getSpouse());
    }

    // --- DTO Conversion ---

    private MemberDTO toDTO(Member m) {
        Long fatherId = m.getFather() != null ? m.getFather().getId() : null;
        String fatherName = m.getFather() != null ? m.getFather().getName() : null;
        Long motherId = m.getMother() != null ? m.getMother().getId() : null;
        String motherName = m.getMother() != null ? m.getMother().getName() : null;
        Long spouseId = m.getSpouse() != null ? m.getSpouse().getId() : null;
        String spouseName = m.getSpouse() != null ? m.getSpouse().getName() : null;

        return new MemberDTO(
                m.getId(),
                m.getName(),
                m.getGender(),
                m.getDateOfBirth(),
                fatherId, fatherName,
                motherId, motherName,
                spouseId, spouseName
        );
    }
}