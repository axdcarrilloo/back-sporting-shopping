package com.bt.controllers;

import com.bt.dtos.ResponseMainDto;
import com.bt.dtos.SaleRegisterDto;
import com.bt.services.SaleService;
import com.bt.utils.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = Route.BASE_PATH)
public class SaleController {
    private final SaleService saleSvc;

    @GetMapping(value = Route.GET_ALL)
    private ResponseEntity<ResponseMainDto> getAll() {
        return new ResponseEntity<>(saleSvc.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = Route.REGISTER)
    public ResponseEntity<ResponseMainDto> register(@RequestBody SaleRegisterDto saleRegisterDto) {
        return new ResponseEntity<>(saleSvc.register(saleRegisterDto), HttpStatus.CREATED);
    }
}
