package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.*;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.*;
import at.ac.tuwien.sepm.groupphase.backend.entity.Employee;
import at.ac.tuwien.sepm.groupphase.backend.entity.Employer;
import at.ac.tuwien.sepm.groupphase.backend.entity.Profile;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployeeService;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployerService;
import at.ac.tuwien.sepm.groupphase.backend.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = "/api/v1/profiles")
public class ProfileEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final RegisterEmployeeMapper registerEmployeeMapper;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    private final RegisterEmployerMapper registerEmployerMapper;
    private final EmployerService employerService;
    private final EmployerMapper employerMapper;

    @Autowired
    public ProfileEndpoint(EmployeeService employeeService, RegisterEmployeeMapper registerEmployeeMapper, RegisterEmployerMapper registerEmployerMapper, EmployerService employerService, EmployerMapper employerMapper, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.registerEmployeeMapper = registerEmployeeMapper;
        this.employeeMapper = employeeMapper;
        this.registerEmployerMapper = registerEmployerMapper;
        this.employerService = employerService;
        this.employerMapper = employerMapper;
    }

    @PostMapping(value = "/employee")
    @ApiOperation(value = "Register a new employee", authorizations = {@Authorization(value = "apiKey")})
    @ResponseStatus(HttpStatus.CREATED)
    public Long registerEmployee(@Valid @RequestBody RegisterEmployeeDto registerEmployeeDto) {
        Employee employee = registerEmployeeMapper.employeeDtoToEmployee(registerEmployeeDto);
        return employeeService.createEmployee(employee);
    }

    @GetMapping(value = "/employee/{email}")
    @ApiOperation(value = "Get an employees profile details", authorizations = {@Authorization(value = "apiKey")})
    public EmployeeDto getEmployee(@PathVariable String email) {
        LOGGER.info("GET /api/v1/profiles/employee/{}", email);
        return employeeMapper.employeeToEmployeeDto(employeeService.findOneByEmail(email));
    }

    @PostMapping(value = "/employer")
    @ApiOperation(value = "Register a new employer", authorizations = {@Authorization(value = "apiKey")})
    @ResponseStatus(HttpStatus.CREATED)
    public Long registerEmployer(@Valid @RequestBody RegisterEmployerDto registerEmployerDto) {
        Employer employer = registerEmployerMapper.employerDtoToEmployer(registerEmployerDto);
        return employerService.createEmployer(employer);
    }

    @GetMapping(value = "/employer/{email}")
    @ApiOperation(value = "Get an employers profile details", authorizations = {@Authorization(value = "apiKey")})
    public EmployerDto getEmployer(@PathVariable String email) {
        LOGGER.info("GET /api/v1/profiles/employer/{}", email);
        return employerMapper.employerToEmployerDto(employerService.findOneByEmail(email));
    }
}
