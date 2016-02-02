package cz.muni.fi.hateoas;

import cz.muni.fi.controllers.SpacemanController;
import cz.muni.fi.dto.SpacemanDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Method;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Vaculik on 02/02/2016.
 */
@Component
public class SpacemanResourceAssembler extends ResourceAssemblerSupport<SpacemanDTO, SpacemanResource> {

    private static final Logger logger = LoggerFactory.getLogger(SpacemanResourceAssembler.class);

    @Autowired
    private EntityLinks entityLinks;

    public SpacemanResourceAssembler() {
        super(SpacemanController.class, SpacemanResource.class);
    }

    @Override
    public SpacemanResource toResource(SpacemanDTO spacemanDTO) {
        SpacemanResource spacemanResource = new SpacemanResource(spacemanDTO);
        Long id = spacemanDTO.getId();
        try {
            Link self = entityLinks.linkToSingleResource(SpacemanDTO.class, id).withSelfRel();
            spacemanResource.add(self);
            Method delete = SpacemanController.class.getMethod("deleteSpaceman", long.class);
            spacemanResource.add(linkTo(SpacemanController.class, delete, id).withRel("delete"));
            Method update = SpacemanController.class.getMethod("updateSpaceman", SpacemanDTO.class, BindingResult.class);
            spacemanResource.add(linkTo(SpacemanController.class, update).withRel("update"));
        } catch (Exception ex) {
            logger.error("Failed to create links: {}", ex);
        }
        return spacemanResource;
    }
}
