package by.zabavskiy.controller;

import by.zabavskiy.controller.request.TaskCreateRequest;
import by.zabavskiy.controller.request.TaskEditRequest;
import by.zabavskiy.domain.Task;
import by.zabavskiy.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
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
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task createUser(@RequestBody TaskCreateRequest taskCreateRequest) {

    Task task = conversionService.convert(taskCreateRequest, Task.class);

    return taskService.save(task);
  }

  @ApiOperation(value = "Endpoint \"All tasks search\"")
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

  @ApiOperation(value = "Endpoint \"Tasks search by params with Function Call where:" +
          " result equal status and startDate - more or equal Start Date and endDate - less or equal End Date\"")
  @GetMapping("/functiancallsearch")
  public ResponseEntity<List<Task>> searchByParamsFunctionCall(
      @RequestParam Long status, @RequestParam Date startDate, @RequestParam Date endDate) {

    return new ResponseEntity<>(
        taskService.searchByParamsFunctionCall(status, startDate, endDate), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint \"Tasks search by param \"Name\" by CriteriaApi\"")
  @GetMapping("/criteriaapisearch")
  public ResponseEntity<List<Task>> searchByParamNameCriteriaApi(String name) {

    return new ResponseEntity<>(taskService.searchByParamNameCriteriaApi(name), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint \"Tasks search by params by HQL (one param to enter)\"")
  @GetMapping("/hqlsearch")
  public ResponseEntity<List<Task>> searchByParamsHQL(Long status, Date startDate, Date endDate) {

    return new ResponseEntity<>(
        taskService.searchByParamsHQL(status, startDate, endDate), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint \"Tasks search by param \"Status\"")
  @GetMapping("/searchbystatus")
  public ResponseEntity<List<Task>> searchByParamStatus(Long status) {

    return new ResponseEntity<>(taskService.searchByParamStatus(status), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint \"Tasks search by param \"Start Date\"")
  @GetMapping("/searchbystartdate")
  public ResponseEntity<List<Task>> searchByParamStartDate(Date startDate) {

    return new ResponseEntity<>(taskService.searchByParamStartDate(startDate), HttpStatus.OK);
  }

  @ApiOperation(value = "Endpoint \"Task's search by param \"End Date\"")
  @GetMapping("/searchbyenddate")
  public ResponseEntity<List<Task>> searchByParamEndDate(Date endDate) {

    return new ResponseEntity<>(taskService.searchByParamEndDate(endDate), HttpStatus.OK);
  }
}
