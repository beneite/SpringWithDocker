package com.beneite.SpringWithDocker.service.implementation;

import com.beneite.SpringWithDocker.constants.GlobalConstants;
import com.beneite.SpringWithDocker.dto.requestDto.CreateEmployeeRequestDto;
import com.beneite.SpringWithDocker.dto.responseDto.CreateEmployeeResponseDto;
import com.beneite.SpringWithDocker.dto.responseDto.GetEmployeeResponseDto;
import com.beneite.SpringWithDocker.entity.EmployeeEntity;
import com.beneite.SpringWithDocker.exception.DuplicateEmailException;
import com.beneite.SpringWithDocker.exception.ResourceNotFoundException;
import com.beneite.SpringWithDocker.mapper.AutoUserMapper;
import com.beneite.SpringWithDocker.repository.EmployeeRepository;
import com.beneite.SpringWithDocker.service.EmployeeService;
import com.beneite.SpringWithDocker.utils.CommonUtilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.beneite.SpringWithDocker.constants.GlobalConstants.DESIGNATIONS;
import static com.beneite.SpringWithDocker.constants.GlobalConstants.BANDS;
import static com.beneite.SpringWithDocker.constants.GlobalConstants.BUSINESS_UNITS;
import static com.beneite.SpringWithDocker.constants.GlobalConstants.DEPARTMENTS;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ConfigServiceImpl configServiceImpl;

    @Override
    public CreateEmployeeResponseDto createEmployeeImplementation(CreateEmployeeRequestDto createEmployeeRequestDto) {

        EmployeeEntity employeeEntity = AutoUserMapper.MAPPER.mapToJpa(createEmployeeRequestDto);

        // checking if the designation, band, business, department is valid or not... from config table
        configServiceImpl.isValidConfigValue(DESIGNATIONS, createEmployeeRequestDto.getDesignation());
        configServiceImpl.isValidConfigValue(BANDS, createEmployeeRequestDto.getBand());
        configServiceImpl.isValidConfigValue(BUSINESS_UNITS, createEmployeeRequestDto.getBusinessUnit());
        configServiceImpl.isValidConfigValue(DEPARTMENTS, createEmployeeRequestDto.getDepartment());

        // creating company email id for new employee
        String companyEmailId = CommonUtilities.createCompanyEmailFromFirstAndLastName(employeeEntity.getFirstName(), employeeEntity.getLastName());
        Optional<EmployeeEntity> ifEmailExist = employeeRepository.findByCompanyEmail(companyEmailId);     // check if email exist in DB
        if(ifEmailExist.isPresent()){
            throw new DuplicateEmailException(companyEmailId);
        }
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

    @Override
    public GetEmployeeResponseDto getEmployeeByCompanyEmailId(String companyEmailId) {
        EmployeeEntity employeeEntity = employeeRepository.findByCompanyEmail(companyEmailId).orElseThrow(
                () -> new ResourceNotFoundException(companyEmailId)
        );

        return AutoUserMapper.MAPPER.mapToGetEmployeeResponseDto(employeeEntity);
    }

    @Override
    public List<GetEmployeeResponseDto> getEmployeeByDepartment(String departmentName) {
        List<EmployeeEntity> employeeEntity = employeeRepository.findByDepartment(departmentName);
        return employeeEntity.stream().map(entity -> AutoUserMapper.MAPPER.mapToGetEmployeeResponseDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<GetEmployeeResponseDto> getEmployeeByBusinessUnit(String businessUnit) {
        List<EmployeeEntity> employeeEntity = employeeRepository.findByBusinessUnit(businessUnit);
        return employeeEntity.stream().map(entity -> AutoUserMapper.MAPPER.mapToGetEmployeeResponseDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<GetEmployeeResponseDto> getEmployeeByBand(String band) {
        List<EmployeeEntity> employeeEntity = employeeRepository.findByBand(band);
        return employeeEntity.stream().map(entity -> AutoUserMapper.MAPPER.mapToGetEmployeeResponseDto(entity)).collect(Collectors.toList());
    }


}
