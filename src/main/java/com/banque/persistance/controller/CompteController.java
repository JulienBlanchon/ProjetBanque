package com.banque.persistance.controller;

import com.banque.persistance.model.Compte;
import com.banque.persistance.service.CompteService;
import com.banque.persistance.service.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/compte")
public class CompteController {
    @Autowired
    private CompteService compteService;

    @GetMapping("/lister")
    public List<Compte> listeComptes(){
        return (List<Compte>) compteService.getComptes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompte(@PathVariable Integer id) {
        Optional<Compte> compte = compteService.getCompte(id);
        return compte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Compte compte(@RequestBody Compte compte){
        Compte c = new Compte();
        c.setNumero(compte.getNumero());
        c.setClient(compte.getClient());
        return compteService.saveCompte(c);
    }

    @PutMapping("/addsolde/{id}")
    public ResponseEntity<Compte> addSolde(@PathVariable Integer id, @RequestBody Compte updatedCompte){
        Optional<Compte> optionalCompte = compteService.getCompte(id);
        if (optionalCompte.isPresent()) {
            Compte c = optionalCompte.get();
            if(c.getSolde() == null){
                c.setSolde(updatedCompte.getSolde());
            }else{
                c.setSolde(c.getSolde().add(updatedCompte.getSolde()));
            }
            compteService.saveCompte(c);
            return ResponseEntity.ok(c);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Integer id, @RequestBody Compte updatedCompte) {
        Optional<Compte> optionalCompte = compteService.getCompte(id);
        if (optionalCompte.isPresent()) {
            Compte c = optionalCompte.get();
            c.setNumero(updatedCompte.getNumero());
            c.setClient(updatedCompte.getClient());
            compteService.saveCompte(c);
            return ResponseEntity.ok(c);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transfert")
    public ResponseEntity<?> virement(@RequestBody TransfertService transfert) {
        Integer expediteurId = transfert.getExpediteur();
        Integer destinataireId = transfert.getDestinataire();
        BigDecimal montant = null;
        if (transfert.getMontant() != null) {
            montant = BigDecimal.valueOf(transfert.getMontant());
        } else {
            return ResponseEntity.badRequest().body("Le montant du virement est null.");
        }

        Optional<Compte> optionalExpediteur = compteService.getCompte(expediteurId);
        Optional<Compte> optionalDestinataire = compteService.getCompte(destinataireId);

        if (optionalExpediteur.isEmpty() || optionalDestinataire.isEmpty()) {
            return ResponseEntity.badRequest().body("Compte expéditeur ou destinataire introuvable.");
        }

        Compte expediteur = optionalExpediteur.get();
        Compte destinataire = optionalDestinataire.get();

        BigDecimal soldeExpediteur = expediteur.getSolde();
        if (soldeExpediteur.compareTo(montant) < 0) {
            return ResponseEntity.badRequest().body("Solde insuffisant pour effectuer le virement.");
        }

        BigDecimal nouveauSoldeExpediteur = soldeExpediteur.subtract(montant);
        BigDecimal nouveauSoldeDestinataire = destinataire.getSolde().add(montant);

        expediteur.setSolde(nouveauSoldeExpediteur);
        destinataire.setSolde(nouveauSoldeDestinataire);

        compteService.saveCompte(expediteur);
        compteService.saveCompte(destinataire);

        return ResponseEntity.ok("Virement effectué avec succès.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompte(@PathVariable Integer id) {
        Optional<Compte> compte = compteService.getCompte(id);
        if (compte.isPresent()) {
            compteService.deleteCompte(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

