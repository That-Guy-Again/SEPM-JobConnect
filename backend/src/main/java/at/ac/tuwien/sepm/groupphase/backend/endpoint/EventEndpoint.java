package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.DetailedEventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventInquiryDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SearchEventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/events")
@Validated
public class EventEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EventService eventService;
    private final EventMapper eventMapper;

    @Autowired
    public EventEndpoint(EventMapper eventMapper, EventService eventService) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping
    @ApiOperation(value = "Publish a new event", authorizations = {@Authorization(value = "apiKey")})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYER')")
    public EventInquiryDto create(@Valid @RequestBody EventInquiryDto eventInquiryDto, @RequestHeader String authorization) {
        LOGGER.info("POST /api/v1/events/{}", eventInquiryDto);

        return eventMapper.eventToEventInquiryDto(
            eventService.saveEvent(eventMapper.eventInquiryDtoToEvent(eventInquiryDto)));

    }

    @GetMapping(value = "/{id}/details")
    @ApiOperation(value = "Get event details")
    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional
    public DetailedEventDto getEventDetails(@PathVariable Long id) {
        LOGGER.info("GET /api/v1/events/{}/details", id);
        return eventMapper.eventToDetailedEventDto(eventService.findById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get list of events")
    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional
    @ResponseBody
    public List<DetailedEventDto> search(SearchEventDto searchEventDto) {
        LOGGER.info("GET /api/v1/events");
        System.out.println(searchEventDto);
        return eventMapper.eventsToDetailedEventDtos(eventService.findAll(searchEventDto));
    }


    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update an event", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasAuthority('ROLE_EMPLOYER')")
    public void update(@Valid @RequestBody EventInquiryDto eventInquiryDto) {
        LOGGER.info("PUT /api/v1/events/{}", eventInquiryDto);
        eventMapper.eventToEventInquiryDto(
            eventService.saveEvent(eventMapper.eventInquiryDtoToEvent(eventInquiryDto)));

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete event and related data")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYER')")
    public void delete(@PathVariable Long id) {
        LOGGER.info("DELETE /api/v1/events/{}", id);
        eventService.deleteEventById(id);
    }

}
