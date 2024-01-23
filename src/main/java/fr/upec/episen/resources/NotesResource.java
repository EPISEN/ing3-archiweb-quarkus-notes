package fr.upec.episen.resources;

import fr.upec.episen.proto.EtudiantGrpc;
import fr.upec.episen.proto.EtudiantNameRequest;
import fr.upec.episen.resources.object.Notes;
import io.quarkus.grpc.GrpcClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Exposer une méthode getAll renvoyant une liste de notes n Integer
 * depuis la route /notes
 *
 * Json attendu :
 *
 * [
 *      "1234567890G" : {
 *          nomEtudiant: "Dupont",
 *          notes: [10, 12, 13, 8, 17]
 *      },
 *      "0987654321G": {
 *           nomEtudiant: "Michel",
 *          notes: [11, 13, 14, 9, 18]
 *      }
 * ]
 */
@Path("/notes")
public class NotesResource {

    @GrpcClient("etudiant")
    EtudiantGrpc.EtudiantBlockingStub etudiantGrpcService;

    public HashMap<String,Notes> hashMap =new HashMap<String, Notes>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String,Notes> getAllNotes(){

        // FIRST STUDENT
        ArrayList<Integer> notes0 = new ArrayList<>(){{
            add(15);
            add(6);
            add(7);
        }};
        Notes etu0 = new Notes();
        String ine0 = "u21815645";
        etu0.setEtudiant(
                etudiantGrpcService.getEtudiantName(
                        EtudiantNameRequest.newBuilder()
                                .setIne(ine0)
                                .build()
                ).getName()
        );
        etu0.setNotes(notes0);

        // SECOND STUDENT
        ArrayList<Integer> notes1 = new ArrayList<>();
        notes1.add(10);
        notes1.add(12);
        notes1.add(13);
        notes1.add(8);
        notes1.add(17);
        Notes etu1 = new Notes();
        String ine1 = "1234567890G";
        etu1.setEtudiant(
                etudiantGrpcService.getEtudiantName(
                        EtudiantNameRequest.newBuilder()
                        .setIne(ine1)
                        .build()
                ).getName()
        );
        etu1.setNotes(notes1);

        // THIRD STUDENT
        ArrayList<Integer> notes2 = new ArrayList<>();
        notes2.add(11);
        notes2.add(13);
        notes2.add(14);
        notes2.add(9);
        notes2.add(18);
        Notes etu2 = new Notes();
        String ine2 = "0987654321G";
        etu2.setEtudiant(
                etudiantGrpcService.getEtudiantName(EtudiantNameRequest.newBuilder()
                    .setIne(ine2)
                    .build()
                ).getName()
        );
        etu2.setNotes(notes2);

        // PUT NOTES IN HASHMAP
        hashMap.put(ine0, etu0);
        hashMap.put(ine1, etu1);
        hashMap.put(ine2, etu2);

        return hashMap;
    }



}
