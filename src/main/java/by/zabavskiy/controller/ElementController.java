package by.zabavskiy.controller;

import by.zabavskiy.controller.request.ElementCreateRequest;
import by.zabavskiy.controller.request.ElementEditRequest;
import by.zabavskiy.domain.Element;
import by.zabavskiy.service.ElementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Element createElement(@RequestBody ElementCreateRequest elementCreateRequest) {

        Element element = conversionService.convert(elementCreateRequest, Element.class);

        return elementService.save(element);
    }


    @ApiOperation(value = "Endpoint \"Get all elements\"")
    @GetMapping
    public ResponseEntity<List<Element>> findAllElements() {

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

        elementService.deleteElementById(elementId);
    }


    @ApiOperation(value = "Endpoint \"Elements search by param \"Value\" by CriteriaApi\"")
    @GetMapping("/criteriaapisearch")
    public ResponseEntity<List<Element>> searchByParamValueCriteriaApi(String value) {

        return new ResponseEntity<>(elementService.searchByParamValueCriteriaApi(value), HttpStatus.OK);
    }


    @ApiOperation(value = "Endpoint \"Elements search by param \"Task Id\"\"")
    @GetMapping("/elementsearchbytaskid")
    public ResponseEntity<List<Element>> searchByParamTaskId(Long taskId) {

        return new ResponseEntity<>(elementService.searchByParamTaskId(taskId), HttpStatus.OK);
    }
}
