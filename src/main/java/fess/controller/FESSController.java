package fess.controller;

import fess.config.model.MVCResponse;
import fess.config.model.MVCResponseError;
import fess.config.model.MVCResponseObject;
import fess.dto.FESS;
import fess.service.implementations.FessService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Slf4j
public class FESSController {

//    private final FessService fessService;
//
//    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
//    public MVCResponse sendTransaction(@RequestBody FESS fess, @RequestParam("threads") Integer numberThreads,
//                                       @RequestParam("interval") Integer interval) {
//        try {
//            fessService.send(fess, numberThreads, interval);
//            return new MVCResponseObject(200, "OK");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new MVCResponseError(400, e.getMessage());
//        }
//    }
}