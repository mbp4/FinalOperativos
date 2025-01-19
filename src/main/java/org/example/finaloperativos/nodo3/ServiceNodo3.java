package org.example.finaloperativos.nodo3;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.example.finaloperativos.Producto;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ServiceNodo3 {

    private final Firestore db;

    public ServiceNodo3(Firestore db) {
        this.db = db;
    }

    // Con esto buscaremos nuestro producto mediante su ID ya que mediante el nombre pueden existir incongruencias
    public Producto obtenerProductoPorId(String productoId) {
        DocumentReference productoRef = db.collection("productos").document(productoId);
        ApiFuture<DocumentSnapshot> future = productoRef.get();

        try {
            com.google.cloud.firestore.DocumentSnapshot documentSnapshot = future.get();
            if (documentSnapshot.exists()) {
                return documentSnapshot.toObject(Producto.class);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al obtener el producto: " + e.getMessage());
        }
        return null;  //en caso de que no exista se devuelve un null
    }
}
