package controllers;

import models.ComputerPart;
import models.ComputerPartWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import sevices.ComputerPartsService;
import utils.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ExampleController {
    static int storedShowType=-1;
    private ComputerPartsService service = new ComputerPartsService();

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public ModelAndView allParts(@RequestParam(defaultValue = "1") int currPage,@RequestParam(defaultValue ="-1") int showType) {
        int partsCount;
        if (storedShowType == -1 && showType == -1){
            storedShowType = 0;
            showType = 0;
        }
        if (showType !=- 1){
            storedShowType = showType;
        }
        else{
            showType = storedShowType;
        }
        List<ComputerPart> parts;
        if(showType == 1) {
            parts = service.getUnnecessaryParts(currPage);
            partsCount = service.getCountUnnecessaryParts();
        }
        else if (showType == 2) {
            parts = service.getNecessaryParts(currPage);
            partsCount = service.getCountNecessaryParts();
        }
        else {
            parts = service.getParts(currPage);
            partsCount = service.getCountParts();
        }
        int pagesCount = (partsCount + 9) / Constants.PAGE_SIZE;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("example");
        modelAndView.addObject("partsList",parts);
        modelAndView.addObject("wholeComputers",service.getWholeComputers());
        modelAndView.addObject("partsCount",partsCount);
        modelAndView.addObject("pagesCount",pagesCount);
        modelAndView.addObject("currPage",currPage);
        modelAndView.addObject("showType",showType);
        return modelAndView ;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("part", service.getPartById(id));
        modelAndView.addObject("isNecessary");
        return  modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPart(@ModelAttribute("part") ComputerPartWrapper part){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/example");
        try{
            service.updatePart(part);
        }
        catch (IllegalArgumentException e){
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("part", new ComputerPart());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute("part") ComputerPartWrapper part){
        ModelAndView modelAndView = new ModelAndView();
         if (service.getPartByName(part.getName())==null){
             service.savePart(part);
             modelAndView.setViewName("redirect:/example");
         }
         else{
             modelAndView.setViewName("redirect:/fail");
             modelAndView.addObject("reason","alreadyExists");
         }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePart(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/example");
        ComputerPart part = service.getPartById(id);
        service.deletePart(part);
        return modelAndView;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");;
        String searchTerm = request.getParameter("searchTerm");
        modelAndView.addObject("search",searchTerm);
        if( searchTerm == null){
            modelAndView.addObject("redirect","search");
        }
        else{
            ComputerPart part=service.getPartByName(searchTerm);
            if (part !=null){
                modelAndView.setViewName("redirect:/edit/"+part.getId());
            }
            else{
                modelAndView.setViewName("redirect:/fail");
                modelAndView.addObject("reason","notFound");
            }
        }

        return  modelAndView;
    }

    @RequestMapping(value = "/fail"  , method = RequestMethod.GET)
    public ModelAndView handleFail(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}