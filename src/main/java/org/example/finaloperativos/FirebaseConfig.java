package org.example.finaloperativos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws IOException {
        // Leer el archivo JSON desde resources
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream(
                "programaciondistribuida-68425-firebase-adminsdk-in7vw-1eebdaef61.json"
        );

        if (serviceAccount == null) {
            throw new IOException("El archivo de configuración de Firebase no se encontró.");
        }

        // Inicializar Firebase con las credenciales
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        System.out.println("Firebase inicializado correctamente.");
        return FirestoreClient.getFirestore();
    }
}


