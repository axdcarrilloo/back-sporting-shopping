package com.bt.services.impls;

import com.bt.domain.repositories.ProductRepository;
import com.bt.dtos.BuildLog;
import com.bt.services.ProductService;
import com.bt.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CustomLogger customLogger;
    private final ProductRepository productRepository;

    private BuildLog buildCustomLogger() {
        return BuildLog.builder().nameClass(this.getClass().getName())
                .nameMethod("existsById()").message("Validando existencia de producto por Id").build()
        ;
    }

    @Override
    public Boolean existsById(Long id) {
        customLogger.logMain(Boolean.FALSE, buildCustomLogger());
        return productRepository.existsById(id);
    }
}
