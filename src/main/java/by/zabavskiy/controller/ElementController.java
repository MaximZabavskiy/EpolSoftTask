package by.zabavskiy.controller;

import by.zabavskiy.controller.request.ElementCreateRequest;
import by.zabavskiy.controller.request.ElementEditRequest;
import by.zabavskiy.controller.request.TaskCreateRequest;
import by.zabavskiy.controller.request.TaskEditRequest;
import by.zabavskiy.domain.Element;
import by.zabavskiy.domain.Task;
import by.zabavskiy.exception.EntityNotFoundException;
import by.zabavskiy.repository.impl.ElementSpringDataRepository;
import by.zabavskiy.service.ElementService;
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
import java.util.List;


@RestController
@RequestMapping("tasks/elements")
@RequiredArgsConstructor
public class ElementController {

    private final ElementService elementService;

    public final ConversionService conversionService;


    @ApiOperation(value = "Endpoint \"Creation of element\"")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation of element"),
            @ApiResponse(code = 422, message = "Failed element creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Element createElement(@Valid @RequestBody ElementCreateRequest elementCreateRequest) {

        Element element = conversionService.convert(elementCreateRequest, Element.class);

        return elementService.save(element);
    }


    @ApiOperation(value = "Endpoint \"All elements search\"")
    @GetMapping
    public ResponseEntity<List<Element>> findAllTasks() {
        return new ResponseEntity<>(elementService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Endpoint \"Element's update\"")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Element updateElement(@RequestBody ElementEditRequest elementEditRequest) {

        Element element = conversionService.convert(elementEditRequest, Element.class);

        return elementService.update(element);
    }


    @ApiOperation(value = "Endpoint \"Element's deletion\"")
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteElementById(@PathVariable("id") Long elementId) {

        elementService.deleteById(elementId);
    }
}
