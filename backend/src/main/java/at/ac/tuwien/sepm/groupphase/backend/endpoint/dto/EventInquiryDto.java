package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Address;
import at.ac.tuwien.sepm.groupphase.backend.entity.Employer;
import at.ac.tuwien.sepm.groupphase.backend.entity.Task;
import org.springframework.security.core.parameters.P;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class EventInquiryDto {

    private Long id;

    @NotNull(message = "Veranstaltungsbeginn muss angegeben sein")
    @Future(message = "Veranstaltungsbeginn muss in der Zukunft liegen")
    private LocalDateTime start;

    @NotNull(message = "Veranstaltungsende muss angegeben sein")
    @Future(message = "Veranstaltungsende muss in der Zukunft liegen")
    private LocalDateTime end;

    @NotNull(message = "Titel muss angegeben sein")
    @NotBlank(message = "Titel darf nicht leer sein")
    @Size(max = 255)
    private String title;

    @NotNull(message = "Beschreibung muss angegeben sein")
    @NotBlank(message = "Beschreibung darf nicht leer sein")
    @Size(max = 1000)
    @Pattern(regexp = "[a-zA-ZÖöÜüÄä]+([ ]|[a-zA-ZÖöÜüÄä]|[0-9]|[.]|[,]|[(]|[)]|[-]|[/]|[^'\\u0027])*", message = "Pattern muss eingehalten werden")
    private String description;

    private EmployerDto employer;

    @NotNull(message = "must not be null")
    @Valid
    private AddressInquiryDto address;

    @Valid
    private Set<TaskInquiryDto> tasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmployerDto getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerDto employer) {
        this.employer = employer;
    }

    public AddressInquiryDto getAddress() {
        return address;
    }

    public void setAddress(AddressInquiryDto address) {
        this.address = address;
    }

    public Set<TaskInquiryDto> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskInquiryDto> tasks) {
        this.tasks = tasks;
    }

    @AssertTrue(message = "Start Datum muss vor dem End Datum liegen")
    public boolean isValidDate() {
        if (end != null && start != null) {
            return end.isAfter(start);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventInquiryDto)) return false;
        EventInquiryDto that = (EventInquiryDto) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(start, that.start) &&
            Objects.equals(end, that.end) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end, title, description, address);
    }

    @Override
    public String toString() {
        return "EventInquiryDto{" +
            "id=" + id +
            ", start=" + start +
            ", end=" + end +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", employer=" + employer +
            ", address=" + address +
            ", tasks=" + tasks +
            '}';
    }

    public static final class EventInquiryDtoBuilder {
        private Long id;
        private LocalDateTime start;
        private LocalDateTime end;
        private String title;
        private String description;
        private EmployerDto employer;
        private AddressInquiryDto address;
        private Set<TaskInquiryDto> tasks;

        private EventInquiryDtoBuilder() {
        }

        public static EventInquiryDtoBuilder aEvent() {
            return new EventInquiryDtoBuilder();
        }

        public EventInquiryDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EventInquiryDtoBuilder withStart(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public EventInquiryDtoBuilder withEnd(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public EventInquiryDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public EventInquiryDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public EventInquiryDtoBuilder withEmployer(EmployerDto employer) {
            this.employer = employer;
            return this;
        }

        public EventInquiryDtoBuilder withAddress(AddressInquiryDto address) {
            this.address = address;
            return this;
        }

        public EventInquiryDtoBuilder withTask(Set<TaskInquiryDto> tasks) {
            this.tasks = tasks;
            return this;
        }

        public EventInquiryDto build() {
            EventInquiryDto event = new EventInquiryDto();
            event.setId(id);
            event.setTitle(title);
            event.setDescription(description);
            event.setStart(start);
            event.setEnd(end);
            event.setEmployer(employer);
            event.setAddress(address);
            event.setTasks(tasks);
            return event;
        }
    }


}
