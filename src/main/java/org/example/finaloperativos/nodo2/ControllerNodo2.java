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

    @GetMapping("/producto/actualizar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productoId", "");
        return "actualizarProducto";
    }

    @GetMapping("/producto/actualizar/{productoId}")
    public String mostrarFormularioActualizar(@PathVariable("productoId") String productoId, Model model) {
        Producto producto = serviceNodo2.obtenerProductoPorId(productoId);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "actualizarProducto";
        } else {
            model.addAttribute("error", "Producto no encontrado");
            return "actualizarProducto";
        }
    }

    @PostMapping("/producto/actualizar")
    public String actualizarCantidadProducto(@ModelAttribute Producto producto, Model model) {
        boolean actualizado = serviceNodo2.actualizarCantidadProducto(producto.getId(), producto.getCantidad());
        if (actualizado) {
            model.addAttribute("mensaje", "Cantidad actualizada con éxito");
        } else {
            model.addAttribute("error", "Error al actualizar la cantidad del producto");
        }
        return "actualizarProducto";
    }

}
