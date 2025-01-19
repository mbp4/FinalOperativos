package org.example.finaloperativos.nodo1;

import org.example.finaloperativos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerNodo1 {
    @Autowired
    private ServiceNodo1 serviceNodo1;

    //metodo para agregar info del producto en un formulario
    @GetMapping("/producto/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "agregarProducto";
    }

    //este metodo procesa el formulario enviado
    @PostMapping("/producto/agregar")
    public String agregarProducto(@ModelAttribute Producto producto) {
        serviceNodo1.agregarProducto(producto);
        return "redirect:/producto/agregar";  // Redirige al formulario después de agregar
    }
}
