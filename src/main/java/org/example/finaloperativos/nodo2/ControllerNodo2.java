package org.example.finaloperativos.nodo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actualizar")
public class ControllerNodo2 {

    private final ServiceNodo2 serviceNodo2;

    @Autowired
    public ControllerNodo2(ServiceNodo2 serviceNodo2) {
        this.serviceNodo2 = serviceNodo2;
    }

    @PutMapping("/{id}")
    public String actualizarCantidad(@PathVariable String id, @RequestParam int cantidad) {
        return serviceNodo2.actualizarCantidadProducto(id, cantidad);
    }
}
