package com.bt.services.impls;

import com.bt.domain.entities.SaleDetailsEntity;
import com.bt.domain.repositories.SaleDetailsRepository;
import com.bt.dtos.*;
import com.bt.exceptions.NotFoundException;
import com.bt.mappers.SaleDetailsMapper;
import com.bt.mappers.SaleMapper;
import com.bt.services.ProductService;
import com.bt.services.SaleDetailsService;
import com.bt.services.SaleService;
import com.bt.utils.Constans;
import com.bt.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleDetailsServiceImpl implements SaleDetailsService {
    private final CustomLogger customLogger;
    private final SaleMapper saleMapper;
    private final SaleDetailsMapper saleDetailsMapper;
    private final ProductService productSvc;
    private final SaleDetailsRepository saleDetailsRepository;

    private BuildLog buildCustomLogger(final String nameMthod, final String message) {
        return BuildLog.builder().nameClass(this.getClass().getName())
                .nameMethod(nameMthod).message(message).build()
        ;
    }

    @Override
    public List<SaleDetailsViewDto> getBySale(SaleViewDto saleView) {
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("getByIdSale()",
                "Consultando detalles de venta por id de venta"))
        ;
        return saleDetailsMapper.convertToViewListFromEntityList(
                saleDetailsRepository.findBySaleEntity(saleMapper.convertToEntityFromView(saleView)))
        ;
    }

    @Override
    public ResponseMainDto register(final List<SaleDetailsRegisterDto> saleDetailsListRegisterDto) {
        try {
            saleDetailsListRegisterDto.forEach(details -> {
                if(Boolean.FALSE.equals(productSvc.existsById(details.getProduct().getId()))) {
                    NotFoundException notFoundException =
                            new NotFoundException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_FAILED)
                                    .response(Constans.LOG_PRODUCT_NOT_EXISTS).build()
                            )
                    ;
                    customLogger.logMain(Boolean.TRUE,
                            buildCustomLogger("register()",
                                    Constans.MSG_RESPONSE_REGISTER_FAILED+": "+Constans.LOG_PRODUCT_NOT_EXISTS
                            )
                    );
                    throw notFoundException;
                }
            });

            customLogger.logMain(Boolean.FALSE, buildCustomLogger("register()",
                    "Registrando detalles de la venta"))
            ;
            List<SaleDetailsEntity> lisT =  saleDetailsMapper.convertToEntityListFromRequestList(saleDetailsListRegisterDto);
            saleDetailsRepository.saveAll(lisT);
            return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_SUCCESS)
                    .response("Detalles de la venta registrados").build()
            ;
        } catch (Exception e) {
            customLogger.logMain(Boolean.TRUE, buildCustomLogger("register()", e.getMessage()));
            throw e;
        }
    }
}
