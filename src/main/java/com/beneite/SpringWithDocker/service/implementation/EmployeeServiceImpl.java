package com.beneite.SpringWithDocker.service.implementation;

import com.beneite.SpringWithDocker.constants.GlobalConstants;
import com.beneite.SpringWithDocker.dto.requestDto.CreateEmployeeRequestDto;
import com.beneite.SpringWithDocker.dto.responseDto.CreateEmployeeResponseDto;
import com.beneite.SpringWithDocker.dto.responseDto.GetEmployeeResponseDto;
import com.beneite.SpringWithDocker.entity.EmployeeEntity;
import com.beneite.SpringWithDocker.exception.ResourceNotFoundException;
import com.beneite.SpringWithDocker.mapper.AutoUserMapper;
import com.beneite.SpringWithDocker.repository.EmployeeRepository;
import com.beneite.SpringWithDocker.service.EmployeeService;
import com.beneite.SpringWithDocker.utils.CommonUtilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public CreateEmployeeResponseDto createEmployeeImplementation(CreateEmployeeRequestDto createEmployeeRequestDto) {

        EmployeeEntity employeeEntity = AutoUserMapper.MAPPER.mapToJpa(createEmployeeRequestDto);

        // creating company email id for new employee
        String companyEmailId = CommonUtilities.createCompanyEmailFromFirstAndLastName(employeeEntity.getFirstName(), employeeEntity.getLastName());
        employeeEntity.setCompanyEmail(companyEmailId);
        employeeEntity.setDateOfJoining(LocalDate.now()); // Generate dateOfJoining in backend

        EmployeeEntity employeeEntitySaved = employeeRepository.save(employeeEntity);

        CreateEmployeeResponseDto createEmployeeResponseDto = AutoUserMapper.MAPPER.mapToDto(employeeEntitySaved);
        createEmployeeResponseDto.setMessage(GlobalConstants.EMPLOYEE_RECORD_SUCCESS);
        return createEmployeeResponseDto;
    }

    @Override
    public List<GetEmployeeResponseDto> getAllEmployeeImplementation() {
        List<EmployeeEntity> listOfEmployeeEntity = employeeRepository.findAll();
        return listOfEmployeeEntity.stream().map(employeeEntity -> AutoUserMapper.MAPPER.mapToGetAllEmployeeResponseDto(employeeEntity)).collect(Collectors.toList());
    }

    @Override
    public GetEmployeeResponseDto getEmployeeImplementation(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
        GetEmployeeResponseDto getEmployeeResponseDto = AutoUserMapper.MAPPER.mapToGetEmployeeResponseDto(employeeEntity);
        return getEmployeeResponseDto;
    }
}
