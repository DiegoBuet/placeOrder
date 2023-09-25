package com.app.web.service;


import com.app.web.entity.StockProducto;
import com.app.web.repository.StockProductoRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockProductServiceImpl {

/*    @Autowired
    private StockProductoRepositorio repositorio;

*//*    @Override
    public StockProducto obtenerStockPorCodigo(Integer stockInventario) {
        return repositorio.findByCodigoProducto(stockInventario);
    }*//*

    @Override
    public List<StockProducto> listarTodosLosStocks() {
        return repositorio.findAll();
    }



    @Override
    public boolean actualizarStock(Integer codigoProducto, Integer cantidad) {
        StockProducto stock = repositorio.findByCodigoProducto(codigoProducto);
        if (stock != null) {
            stock.setStockInventario(stock.getStockInventario() + cantidad);
            repositorio.save(stock);
            return true; // Actualización exitosa
        } else {
            return false; // Producto no encontrado, actualización fallida
        }
    }

    @PostConstruct
    public void inicializarStockInicial() {
        // Verifica si ya existen registros de productos en la base de datos
        List<StockProducto> stocks = repositorio.findAll();
        if (stocks.isEmpty()) {
            // Si no existen registros, crea registros con stock inicial de 10
            for (int codigoProducto = 239; codigoProducto <= 384; codigoProducto++) {
                StockProducto nuevoStock = new StockProducto();
                nuevoStock.setCodigoProducto(codigoProducto);
                nuevoStock.setStockInventario(10);
                repositorio.save(nuevoStock);
            }
        }
    }*/
}
