<template>
    <h1> Détail du compte </h1>
    <v-table>
        <thead>
            <tr>
                <th class="text-center">Identifiant</th>
                <th class="text-center">Numéro</th>
                <th class="text-center"> Propriétaire</th>

            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="text-center">{{ compte.id}}</td>
                <td class="text-center">{{ compte.numero }}</td>
                <td><v-btn class="text-center" :to="{path : '/client/'+ client.id}" >{{client.nom}} {{client.prenom}}</v-btn></td>
            </tr>
        </tbody>
    </v-table>
    <v-btn class="btnmodify" style="color: white;margin-right: 20px; background-color: #FCBA04" :to="{path : '/compte/editer/'+ compte.id}">
        <svg-icon type="mdi" :path="pathEdit"></svg-icon>
        Modifier
    </v-btn>
    <v-btn class="btndelete" style="color: white; margin-left: 20px; background-color: #A50104" @click="deleteCompte(compte.id)">
        <svg-icon type="mdi" :path="pathDelete"></svg-icon>
        Supprimer
    </v-btn>
</template>
<script>
import axios from "axios";
import SvgIcon from "@jamescoyle/vue-icon";
import {mdiPencilBox, mdiDelete } from "@mdi/js";

export default {
    components: {SvgIcon},
    data(){
        return{
            compte: [],
            client: [],
            pathEdit: mdiPencilBox,
            pathDelete:mdiDelete
        }
    },
    methods: {
        fetchMessages(){
            var url = window.location.pathname;
            const id = url.substring(url.lastIndexOf('/') + 1);
            axios.get(`http://localhost:8080/api/compte/` + id  )
                .then(response => {
                    this.compte = response.data;
                    console.log(this.compte)
                    axios.get(`http://localhost:8080/api/client/` + this.compte.id_client)
                        .then(response => {
                            this.client = response.data
                        })
                        .catch(error => {
                            console.error("Erreur lors de la récupération des messages reçus: ", error);
                            // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                        });
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                });

        },
        deleteCompte(id) {
            axios.delete("http://localhost:8080/api/compte/"+ id)
                .then(() => {
                    document.location = "http://localhost:8081/compte/lister"
                    alert("Le compte a été supprimé")
                })
                .catch(error => {
                    console.error("Erreur lors de la suppression du compte: ", error);
                });
        },
    },
    created() {
        // Appelez la méthode lors du montage du composant
        this.fetchMessages();
    }
};
</script>

<style>
.btndelete:hover{
    background-color: #590004 !important;
}

.btnmodify:hover{
    background-color: #DEA402 !important;
}
</style>