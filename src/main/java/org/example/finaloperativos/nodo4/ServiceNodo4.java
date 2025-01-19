package org.example.finaloperativos.nodo4;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.example.finaloperativos.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ServiceNodo4 {

    private final Firestore db;

    public ServiceNodo4(Firestore db) {
        this.db = db;
    }

    //metodo que nos decuelva una lista con todos los producto y su info
    public List<Producto> obtenerTodosLosProductos() {
        CollectionReference productosRef = db.collection("productos");
        ApiFuture<QuerySnapshot> future = productosRef.get();

        try {
            QuerySnapshot querySnapshot = future.get();
            return querySnapshot.toObjects(Producto.class);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error al obtener los productos: " + e.getMessage());
        }
        return null;
    }
}
