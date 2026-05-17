package com.hm.hospitalmanagement.controller;


import com.hm.hospitalmanagement.dto.DoctorResponseDto;
import com.hm.hospitalmanagement.dto.OnboardDoctorRequestDto;
import com.hm.hospitalmanagement.dto.PatientResponseDto;
import com.hm.hospitalmanagement.service.DoctorService;
import com.hm.hospitalmanagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnboardDoctorRequestDto onboardDoctorRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onboardDoctorRequestDto));
    }
}
