package com.example.familytree.controller;

import com.example.familytree.dto.MemberCreateUpdateDTO;
import com.example.familytree.dto.MemberDTO;
import com.example.familytree.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // --- CRUD Endpoints ---

    @GetMapping
    public List<MemberDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MemberDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> create(@RequestBody MemberCreateUpdateDTO dto) {
        MemberDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable Long id, @RequestBody MemberCreateUpdateDTO dto) {
        MemberDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- Relationship Endpoints ---

    @GetMapping("/{id}/children")
    public List<MemberDTO> getChildren(@PathVariable Long id) {
        return service.findChildren(id);
    }

    @GetMapping("/{id}/siblings")
    public List<MemberDTO> getSiblings(@PathVariable Long id) {
        return service.findSiblings(id);
    }

    @GetMapping("/{id}/spouse")
    public ResponseEntity<MemberDTO> getSpouse(@PathVariable Long id) {
        MemberDTO spouse = service.findSpouse(id);
        return ResponseEntity.ok(spouse);
    }
}