package org.example.finaloperativos.nodo2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import org.example.finaloperativos.Producto;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.concurrent.ExecutionException;

@Service
public class ServiceNodo2 {

    private final Firestore db;

    public ServiceNodo2(Firestore db) {
        this.db = db;
    }

    public void actualizarProducto(String productoId, Producto producto) {
        DocumentReference productoRef = db.collection("productos").document(productoId);

        ApiFuture<WriteResult> result = productoRef.set(producto);

        try {
            result.get();
            System.out.println("La cantidad del producto ha sido actualizada");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public Producto obtenerProductoPorId(String productoId) {
        DocumentReference productoRef = db.collection("productos").document(productoId);
        ApiFuture<com.google.cloud.firestore.DocumentSnapshot> future = productoRef.get();

        try {
            com.google.cloud.firestore.DocumentSnapshot documentSnapshot = future.get();
            if (documentSnapshot.exists()) {
                return documentSnapshot.toObject(Producto.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al obtener el producto: " + e.getMessage());
        }
        return null;
    }
}
