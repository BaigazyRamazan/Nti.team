package com.ntiteam.Test.Lord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lords")
public class LordController {

    private final LordService lordService;

    @Autowired
    public LordController(LordService lordService) {
        this.lordService = lordService;
    }

    @GetMapping
    public List<Lord> getLords(){
        return lordService.getLords();
    }

    /** This function use Rest Api's method Post to create a new lord.
     *
     * @param lord
     * @return lord
     */
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Lord createLord(@RequestBody Lord lord){
        lordService.createLord(lord);

        return lord;
    }

    /** Display lords that hasn't planet
     *
     * @return list of lords
     */
    @GetMapping("/bezdelniki")
    public List<Lord> getBezdelniki(){
        return lordService.getBezdelniki();
    }

    /** Display 10 youngest lords by ascending order
     *
     * @return list of lords
     */
    @GetMapping(value = "/10youngest",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Lord> getTenYoungestLords(){
        return lordService.getTenYoungestLords();
    }
}
