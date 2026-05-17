package com.hm.hospitalmanagement.service;

import com.hm.hospitalmanagement.dto.DoctorResponseDto;
import com.hm.hospitalmanagement.dto.OnboardDoctorRequestDto;
import com.hm.hospitalmanagement.entity.Doctor;
import com.hm.hospitalmanagement.entity.User;
import com.hm.hospitalmanagement.entity.type.RoleType;
import com.hm.hospitalmanagement.repository.DoctorRepository;
import com.hm.hospitalmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnboardDoctorRequestDto onboardDoctorRequestDto) {
        User user = userRepository.findById(onboardDoctorRequestDto.getUserId()).orElseThrow();

        if(doctorRepository.existsById(onboardDoctorRequestDto.getUserId())){
            throw new IllegalArgumentException("Already a doctor");
        }

        Doctor doctor = Doctor.builder()
                .name(onboardDoctorRequestDto.getName())
                .specialization(onboardDoctorRequestDto.getSpecialization())
                .user(user)
                .build();
        user.getRoles().add(RoleType.DOCTOR);

        return modelMapper.map(doctorRepository.save(doctor),DoctorResponseDto.class);
    }
}
