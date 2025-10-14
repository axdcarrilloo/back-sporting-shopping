package com.bt.services.impls;

import com.bt.domain.repositories.ProductRepository;
import com.bt.dtos.BuildLog;
import com.bt.dtos.ProductRegisterDto;
import com.bt.dtos.ResponseMainDto;
import com.bt.exceptions.AlreadyExistsException;
import com.bt.exceptions.NotFoundException;
import com.bt.mappers.ProductMapper;
import com.bt.services.ProductService;
import com.bt.utils.Constans;
import com.bt.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CustomLogger customLogger;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    private BuildLog buildCustomLogger(final String nameMethod, final String message) {
        return BuildLog.builder().nameClass(this.getClass().getName())
                .nameMethod(nameMethod).message(message).build()
        ;
    }

    @Override
    public ResponseMainDto deleteById(Long id) {
        if(Boolean.FALSE.equals(productRepository.existsById(id))) {
            NotFoundException notFoundException =
                    new NotFoundException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_DELETE_FAILED)
                            .response(Constans.LOG_PRODUCT_NOT_EXISTS).build()
                    )
            ;
            customLogger.logMain(Boolean.TRUE,
                    buildCustomLogger("deleteById()", Constans.MSG_RESPONSE_DELETE_FAILED
                            +": "+Constans.LOG_PRODUCT_NOT_EXISTS)
                    )
            ;
            throw notFoundException;
        }
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("deleteById()",
                "Eliminando producto por id: " + id))
        ;
        productRepository.deleteById(id);
        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_DELETE_SUCCESSFUL).response(id).build();
    }

    @Override
    public ResponseMainDto getAll() {
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("getAll()",
                "Consultando todos los productos...!"))
        ;
        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_QUERY_SUCCESS)
                .response(productMapper.convertToViewListFromEntityList(productRepository.findAll())).build()
        ;
    }

    @Override
    public ResponseMainDto register(final ProductRegisterDto productRegisterDto) {
        if(Boolean.TRUE.equals(productRepository.existsByNombre(productRegisterDto.getNombre()))) {
            AlreadyExistsException alreadyExistsException =
                    new AlreadyExistsException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_FAILED)
                            .response(Constans.LOG_PRODUCT_EXISTS).build()
                    )
            ;
            customLogger.logMain(Boolean.TRUE,
                    buildCustomLogger("register()", Constans.MSG_RESPONSE_REGISTER_FAILED
                            +": "+ Constans.LOG_PRODUCT_EXISTS)
                    )
            ;
            throw alreadyExistsException;
        }

        customLogger.logMain(Boolean.FALSE, buildCustomLogger("register()",
                "Registrando producto..!"))
        ;

        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_SUCCESS)
                .response(
                        productRepository.save(productMapper.convertToEntityFromRequest(productRegisterDto))
                ).build()
        ;
    }
}
