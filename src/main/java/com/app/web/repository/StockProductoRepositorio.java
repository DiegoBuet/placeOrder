package com.app.web.repository;

import com.app.web.entity.StockProducto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StockProductoRepositorio extends JpaRepository<StockProducto, Long>{
        StockProducto findByCodigoProducto(Integer codigoProducto);
}
