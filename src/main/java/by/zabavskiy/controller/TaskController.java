package by.zabavskiy.controller;

import by.zabavskiy.controller.request.TaskCreateRequest;
import by.zabavskiy.controller.request.TaskEditRequest;
import by.zabavskiy.domain.Task;
import by.zabavskiy.repository.impl.TaskSpringDataRepository;
import by.zabavskiy.service.TaskService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TemporalType;
import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    public final ConversionService conversionService;


    @ApiOperation(value = "Endpoint \"Creation of tasks\"")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation of task"),
            @ApiResponse(code = 422, message = "Failed task creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createUser(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {

        Task task = conversionService.convert(taskCreateRequest, Task.class);

        return taskService.save(task);
    }


    @ApiOperation(value = "Endpoint \"All task's search\"")
    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Endpoint \"Task's update\"")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@RequestBody TaskEditRequest taskEditRequest) {

        Task task = conversionService.convert(taskEditRequest, Task.class);

        return taskService.update(task);
    }


    @ApiOperation(value = "Endpoint \"Task's deletion\"")
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaskById(@PathVariable Long id) {

        taskService.deleteById(id);
    }


//    @ApiOperation(value = "Endpoint \"Task's search by params\"")
//    @GetMapping("/search")
//    public ResponseEntity<List<Task>> searchByParams(@RequestParam String name, @RequestParam Long status, @RequestParam Date startDate, @RequestParam Date endDate) {
//        return new ResponseEntity<>(taskService.searchByParams(name, status, startDate, endDate), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Endpoint \"Task's search by params\"")
//    @GetMapping("/search")
//    public ResponseEntity<List<Task>> searchByParamsCriteriaApi(@ModelAttribute SearchCriteria searchCriteria) {
//        return new ResponseEntity<>(taskService.searchByParamsCriteriaApi(searchCriteria), HttpStatus.OK);
//    }

    @ApiOperation(value = "Endpoint \"Task's search by params\"")
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchByParamsCriteriaApi(String name, Long status, Date startDate, Date endDate) {
        return new ResponseEntity<>(taskService.searchByParamsCriteriaApi(name, status, startDate, endDate), HttpStatus.OK);
    }
}
