package org.example.finaloperativos.nodo2;

import org.example.finaloperativos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerNodo2 {

    private final ServiceNodo2 serviceNodo2;

    @Autowired
    public ControllerNodo2(ServiceNodo2 serviceNodo2) {
        this.serviceNodo2 = serviceNodo2;
    }

    @GetMapping("/producto/actualizar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productoId", "");
        return "actualizarProducto";
    }

    @PostMapping("/producto/actualizar")
    public String obtenerProducto(@RequestParam("productoId") String productoId, Model model) {
        // Obtener el producto por su ID
        Producto producto = serviceNodo2.obtenerProductoPorId(productoId);
        if (producto != null) {
            model.addAttribute("producto", producto);  // Pasar el producto a la vista
            model.addAttribute("productoId", productoId);
            return "actualizarCantidadProducto";  // Mostrar formulario para actualizar la cantidad
        } else {
            model.addAttribute("error", "Producto no encontrado");
            return "error";  // Volver al formulario de búsqueda si no se encuentra
        }
    }

    // Segundo formulario para actualizar la cantidad del producto
    @PostMapping("/producto/actualizar/cantidad")
    public String actualizarCantidad(@RequestParam("productoId") String productoId,
                                     @RequestParam("cantidad") int cantidad, Model model) {

        String producto = serviceNodo2.actualizarCantidadProducto(productoId, cantidad);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "productoActualizado";
        } else {
            model.addAttribute("error", "No se pudo actualizar la cantidad");
            return "error";
        }
    }
}
