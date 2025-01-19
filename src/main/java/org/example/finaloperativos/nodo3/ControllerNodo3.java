package org.example.finaloperativos.nodo3;

import org.example.finaloperativos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerNodo3 {
    @Autowired
    private ServiceNodo3 serviceNodo3;

    @GetMapping("/producto/buscar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productoId", "");
        return "buscarProducto";
    }

    // Procesar la búsqueda y mostrar la información del producto
    @PostMapping("/producto/buscar")
    public String buscarProducto(@ModelAttribute("productoId") String productoId, Model model) {
        // Obtener el producto por su ID
        Producto producto = serviceNodo3.obtenerProductoPorId(productoId);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("productoId", productoId);
        } else {
            model.addAttribute("error", "Producto no encontrado");
        }
        return "buscarProducto";
    }
}
