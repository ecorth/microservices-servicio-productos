package com.eco.springboot.app.productos.models.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eco.springboot.app.productos.models.entity.Producto;
import com.eco.springboot.app.productos.models.service.ProductoServiceImpl;

@RestController
public class ProductoController {
	private final Logger log = Logger.getLogger(this.getClass().getName());

	@Value("${server.port}")
	private Integer puerto;
	
	@Autowired
	private ProductoServiceImpl productoServiceImpl;
	
	@GetMapping("/listar")
	public List<Producto> lista(){
		log.log(Level.INFO,"Llamando listar, puerto : {0}", puerto);
		return productoServiceImpl.findAll().stream().peek(producto ->
				producto.setPort(puerto)).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto lista(@PathVariable("id") Long id){
		Producto prod = productoServiceImpl.findById(id);
		prod.setPort(puerto);
		log.log(Level.INFO, "Se llamo esta api");
		return prod;
	}

}
