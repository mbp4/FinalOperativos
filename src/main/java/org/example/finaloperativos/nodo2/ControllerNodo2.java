package org.example.finaloperativos.nodo2;

import org.example.finaloperativos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerNodo2 {
    @Autowired
    private ServiceNodo2 serviceNodo2;

    // Mostramos el formulario para poder usar nuestro progrmaa
    @GetMapping("/producto/actualizar/{id}")
    public String mostrarFormulario(@PathVariable("id") String id, Model model) {
        Producto producto = serviceNodo2.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "actualizarProducto";
        } else {
            model.addAttribute("error", "Producto no encontrado");
            return "error";
        }
    }

    //con este metodo procesaremos la busqeuda del usuario
    @PostMapping("/producto/actualizar/{id}")
    public String actualizarProducto(@PathVariable("id") String id, @ModelAttribute Producto producto) {
        serviceNodo2.actualizarProducto(id, producto);
        return "redirect:/producto/actualizar/" + id;
    }
}
