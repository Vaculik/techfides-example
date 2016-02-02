package cz.muni.fi.controllers;

import cz.muni.fi.exceptions.InvalidRequestFormatException;
import cz.muni.fi.exceptions.ResourceNotFoundException;
import cz.muni.fi.dto.SpacemanDTO;
import cz.muni.fi.facade.SpacemanFacade;
import cz.muni.fi.hateoas.SpacemanResource;
import cz.muni.fi.hateoas.SpacemanResourceAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Vaculik on 02/02/2016.
 */

@RestController
@RequestMapping("/spacemen")
@ExposesResourceFor(SpacemanDTO.class)
public class SpacemanController {

    private static final Logger logger = LoggerFactory.getLogger(SpacemanController.class);

    @Autowired
    private SpacemanFacade spacemanFacade;
    @Autowired
    private SpacemanResourceAssembler spacemanResourceAssembler;


    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<SpacemanResource>> getAllSpacemen() {
        logger.debug("GET all spacemen.");
        List<SpacemanDTO> spacemenDTO = spacemanFacade.getAll();
        Link createLink = linkTo(SpacemanController.class).slash("create").withRel("create");

        Resources<SpacemanResource> spacemanResources = new Resources<>(
                spacemanResourceAssembler.toResources(spacemenDTO),
                linkTo(SpacemanController.class).withSelfRel(),
                createLink);

        return new ResponseEntity<>(spacemanResources, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<SpacemanResource> getSpaceman(@PathVariable long id) {
        logger.debug("GET spaceman with id={}", id);
        SpacemanDTO spacemanDTO = spacemanFacade.getById(id);

        if (spacemanDTO == null) {
            String msg = "Spaceman with id=" + id + " not found.";
            logger.debug(msg);
            throw new ResourceNotFoundException(msg);
        }

        SpacemanResource resource = spacemanResourceAssembler.toResource(spacemanDTO);

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSpaceman(@RequestBody @Valid SpacemanDTO spacemanDTO, BindingResult bindingResult) {
        logger.debug("POST new spaceman: {}", spacemanDTO);
        if (bindingResult.hasErrors()) {
            String msg = "Validation failed when create new spaceman: " + bindingResult.toString();
            logger.error(msg);
            throw new InvalidRequestFormatException(msg);
        }

        spacemanFacade.create(spacemanDTO);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSpaceman(@PathVariable long id) {
        logger.debug("DELETE spaceman with id={}", id);
        SpacemanDTO spacemanDTO = spacemanFacade.getById(id);
        if (spacemanDTO == null) {
            String msg = "Spaceman with id=" + id + " not found.";
            logger.debug(msg);
            throw new ResourceNotFoundException(msg);
        }

        spacemanFacade.delete(spacemanDTO);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateSpaceman(@RequestBody @Valid SpacemanDTO spacemanDTO, BindingResult bindingResult) {
        logger.debug("POST update spaceman: {}", spacemanDTO);
        if (bindingResult.hasErrors()) {
            String msg = "Validation failed when update spaceman: " + bindingResult.toString();
            logger.error(msg);
            throw new InvalidRequestFormatException(msg);
        }

        spacemanFacade.update(spacemanDTO);
    }

}
