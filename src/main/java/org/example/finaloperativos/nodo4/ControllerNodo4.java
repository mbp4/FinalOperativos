package org.example.finaloperativos.nodo4;

import org.example.finaloperativos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerNodo4 {
    @Autowired
    private ServiceNodo4 serviceNodo4;

    // mostramos el listado de los productos
    @GetMapping("/productos")
    public String listarProductos(Model model) {
        List<Producto> productos = serviceNodo4.obtenerTodosLosProductos();
        if (productos != null && !productos.isEmpty()) {
            model.addAttribute("productos", productos);
        } else {
            model.addAttribute("error", "No se encontraron productos");
        }
        return "listarProductos";
    }
}
