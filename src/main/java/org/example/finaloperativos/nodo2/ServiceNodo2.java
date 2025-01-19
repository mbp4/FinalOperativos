package org.example.finaloperativos.nodo2;

import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ServiceNodo2 {

    private final Firestore db;

    public ServiceNodo2(Firestore db) {
        this.db = db;
    }

    public String actualizarCantidadProducto(String idProducto, int nuevaCantidad) {
        try {
            DocumentReference docRef = db.collection("productos").document(idProducto);
            DocumentSnapshot snapshot = docRef.get().get();

            if (snapshot.exists()) {
                int cantidadActual = snapshot.getLong("cantidad").intValue();

                if (nuevaCantidad < 0 && Math.abs(nuevaCantidad) > cantidadActual) {
                    return "Error: No puedes retirar más unidades de las disponibles.";
                }

                int nuevaCantidadFinal = cantidadActual + nuevaCantidad;
                docRef.update("cantidad", nuevaCantidadFinal).get();

                return "Cantidad actualizada correctamente. Nueva cantidad: " + nuevaCantidadFinal;
            } else {
                return "Error: Producto no encontrado.";
            }
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablecer el estado interrumpido
            return "Error al actualizar la cantidad: " + e.getMessage();
        }
    }
}
