package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.model.Brand;
import com.example.demo.repository.BrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    // Injection de dépendance
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("")
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    
    }
    @PostMapping("")
    public Brand createBrand(@Valid @RequestBody Brand brand) {
        return (Brand) brandRepository.save(brand);
    
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable(value = "id") Long brandId) throws Throwable {
        return this.fetchBrand(brandId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable(value = "id") Long brandId) throws Throwable {
        Brand brand= this.fetchBrand(brandId);
        brandRepository.delete(brand);
    }

    


    public Brand fetchBrand(Long brandId) throws Throwable {
        Object brand = brandRepository.findById(brandId)
                .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found")
        );
        return (Brand) brand;
    }
}
