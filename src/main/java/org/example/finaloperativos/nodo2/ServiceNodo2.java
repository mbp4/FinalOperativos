package org.example.finaloperativos.nodo2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
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

    //con este metodo encontramos por id
    public Producto obtenerProductoPorId(String productoId) {
        DocumentReference productoRef = db.collection("productos").document(productoId);
        ApiFuture<DocumentSnapshot> future = productoRef.get();

        try {
            DocumentSnapshot documentSnapshot = future.get();
            if (documentSnapshot.exists()) {
                return documentSnapshot.toObject(Producto.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al obtener el producto: " + e.getMessage());
        }
        return null;  // En caso de que no exista, se devuelve un null
    }

    //con esto actualizaremos el producto encontrado
    public boolean actualizarCantidadProducto(String productoId, int nuevaCantidad) {
        DocumentReference productoRef = db.collection("productos").document(productoId);
        ApiFuture<WriteResult> future = productoRef.update("cantidad", nuevaCantidad);
        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al actualizar la cantidad del producto: " + e.getMessage());
            return false;
        }
    }
}
