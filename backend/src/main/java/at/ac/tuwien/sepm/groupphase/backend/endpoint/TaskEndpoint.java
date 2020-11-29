package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TaskDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TaskInquiryDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TaskMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.TaskService;
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
@RequestMapping(value = "/api/v1/tasks")
public class TaskEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskEndpoint(TaskMapper taskMapper, TaskService taskService) {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Publish a new task", authorizations = {@Authorization(value = "apiKey")})
    public TaskDto create(@Valid @RequestBody TaskInquiryDto taskDto) {
        LOGGER.info("POST /api/v1/tasks/{}", taskDto);

        return taskMapper.taskToTaskDto(
            taskService.saveTask(taskMapper.taskInquiryDtoToTask(taskDto)));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a task", authorizations = {@Authorization(value = "apiKey")})
    public TaskDto update(@Valid @RequestBody TaskInquiryDto taskDto) {
        LOGGER.info("PUT /api/v1/tasks/{}", taskDto);

        return taskMapper.taskToTaskDto(
            taskService.saveTask(taskMapper.taskInquiryDtoToTask(taskDto)));
    }


}
