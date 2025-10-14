package com.bt.controllers;

import com.bt.dtos.ProductRegisterDto;
import com.bt.dtos.ResponseMainDto;
import com.bt.services.ProductService;
import com.bt.utils.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = Route.BASE_PATH)
public class ProductController {
    private final ProductService productSvc;

    @DeleteMapping(value = Route.DELETE)
    private ResponseEntity<ResponseMainDto> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(productSvc.deleteById(id), HttpStatus.OK);
    }

    @GetMapping(value = Route.GET_ALL)
    private ResponseEntity<ResponseMainDto> getAll() {
        return new ResponseEntity<>(productSvc.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = Route.REGISTER)
    public ResponseEntity<ResponseMainDto> register(@RequestBody ProductRegisterDto productRegisterDto) {
        return new ResponseEntity<>(productSvc.register(productRegisterDto), HttpStatus.CREATED);
    }
}
