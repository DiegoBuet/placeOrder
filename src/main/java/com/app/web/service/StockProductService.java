package com.app.web.service;

import com.app.web.entity.StockProducto;

import java.util.List;

public interface StockProductService {
    StockProducto obtenerStockPorCodigo(Integer codigoProducto);

    List<StockProducto> listarTodosLosStocks();

    boolean actualizarStock(Integer codigoProducto, Integer cantidad);
}
