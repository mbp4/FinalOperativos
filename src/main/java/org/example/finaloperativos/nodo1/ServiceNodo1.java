package org.example.finaloperativos.nodo1;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.example.finaloperativos.Producto;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ServiceNodo1 {
    private final Firestore db;

    public ServiceNodo1(Firestore db) {
        this.db = db;
    }

    public void agregarProducto(Producto producto) {
        DocumentReference productoRef = db.collection("productos").document();

        // El producto tendra un ID autogenerado
        ApiFuture<WriteResult> result = productoRef.set(producto);

        try {
            WriteResult writeResult = result.get();
            System.out.println("Producto agregado con éxito: " + writeResult.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al agregar el producto: " + e.getMessage());
        }
    }
}
