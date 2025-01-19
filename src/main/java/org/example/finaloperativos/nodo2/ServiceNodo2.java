package org.example.finaloperativos.nodo2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.example.finaloperativos.Producto;
import org.springframework.stereotype.Service;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Map;
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
                Map<String, Object> producto = snapshot.getData();
                producto.put("cantidad", nuevaCantidad);

                docRef.set(producto, SetOptions.merge());
                return "Cantidad actualizada correctamente";
            } else {
                return "Producto no encontrado";
            }
        } catch (Exception e) {
            return "Error al actualizar la cantidad: " + e.getMessage();
        }
    }
}
