package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Task;
import at.ac.tuwien.sepm.groupphase.backend.util.Gender;
import at.ac.tuwien.sepm.groupphase.backend.util.annotation.IsAdult;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class EmployeeDto {

    private Long id;

    @Valid
    @NotNull(message = "Profildetails müssen angegeben sein")
    private ProfileDto profileDto;

    private Set<Task> tasks;

    private Set<InterestDto> interestDtos;

    @NotNull(message = "Geschlecht muss angegeben sein")
    private Gender gender;

    @NotNull(message = "Geburtstag muss angegeben sein")
    @IsAdult(message = "Benutzer müssen volljährig sein")
    private LocalDateTime birthDate;

    private Set<TimeDto> times;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<InterestDto> getInterestDtos() {
        return interestDtos;
    }

    public void setInterestDtos(Set<InterestDto> interestDtos) {
        this.interestDtos = interestDtos;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Set<TimeDto> getTimes() {
        return times;
    }

    public void setTimes(Set<TimeDto> times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto employee = (EmployeeDto) o;
        return Objects.equals(id, employee.id) &&
            Objects.equals(profileDto, employee.profileDto) &&
            Objects.equals(gender, employee.gender) &&
            Objects.equals(birthDate, employee.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileDto, gender, birthDate);
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
            "id=" + id +
            ", profileDto=" + profileDto +
            ", tasks=" + tasks +
            ", interestDtos=" + interestDtos +
            ", gender=" + gender +
            ", birthDate=" + birthDate +
            ", times=" + times +
            '}';
    }

    public static final class EmployeeDtoBuilder{
        private Long id;
        private ProfileDto profileDto;
        private Set<Task> tasks;
        private Set<InterestDto> interestDtos;
        private Gender gender;
        private LocalDateTime birthDate;
        private Set<TimeDto> times;

        private EmployeeDtoBuilder(){}

        public static EmployeeDto.EmployeeDtoBuilder aEmployeeDto(){
            return new EmployeeDto.EmployeeDtoBuilder();
        }
        public EmployeeDto.EmployeeDtoBuilder withProfileDto(ProfileDto profileDto){
            this.id = profileDto.getId();
            this.profileDto = profileDto;
            return this;
        }
        public EmployeeDto.EmployeeDtoBuilder withTasks(Set<Task> tasks){
            this.tasks = tasks;
            return this;
        }
        public EmployeeDto.EmployeeDtoBuilder withInterestDtos(Set<InterestDto> interestDtos){
            this.interestDtos = interestDtos;
            return this;
        }
        public EmployeeDto.EmployeeDtoBuilder withGender(Gender gender){
            this.gender = gender;
            return this;
        }
        public EmployeeDto.EmployeeDtoBuilder withBirthDate(LocalDateTime birthDate){
            this.birthDate = birthDate;
            return this;
        }
        public EmployeeDto.EmployeeDtoBuilder withTimes(Set<TimeDto> times){
            this.times = times;
            return this;
        }

        public EmployeeDto build(){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(id);
            employeeDto.setProfileDto(profileDto);
            employeeDto.setInterestDtos(interestDtos);
            employeeDto.setTasks(tasks);
            employeeDto.setGender(gender);
            employeeDto.setBirthDate(birthDate);
            employeeDto.setTimes(times);
            return employeeDto;
        }
    }
}
