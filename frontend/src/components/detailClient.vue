<template>
    <h1> Détail du client </h1>
    <v-table>
        <thead>
            <tr>
                <th class="text-center">Identifiant</th>
                <th class="text-center">Nom</th>
                <th class="text-center">Prénom</th>

            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="text-center">{{ messages.id}}</td>
                <td class="text-center">{{ messages.nom }}</td>
                <td class="text-center">{{messages.prenom}}</td>
            </tr>
        </tbody>
    </v-table>
    <v-btn class="btnmodify" style="color: white;margin-right: 20px; background-color: #FCBA04" :to="{path : '/client/editer/'+ messages.id}">
        <svg-icon type="mdi" :path="pathEdit"></svg-icon>
        Modifier
    </v-btn>
    <v-btn class="btndelete" style="color: white; margin-left: 20px; background-color: #A50104" @click="deleteClient(messages.id)">
        <svg-icon type="mdi" :path="pathDelete"></svg-icon>
        Supprimer
    </v-btn>
</template>

<script>
import axios from "axios";
import SvgIcon from '@jamescoyle/vue-icon';
import {mdiPencilBox, mdiDelete } from "@mdi/js";

export default {
    name: "my-component",
    components: {
        SvgIcon
    },
    data(){
        return{
            messages: [],
            pathEdit: mdiPencilBox,
            pathDelete:mdiDelete
        }
    },
    methods: {
        fetchMessages(){
            var url = window.location.pathname;
            const id = url.substring(url.lastIndexOf('/') + 1);
            axios.get(`http://localhost:8080/api/client/` + id  )
                .then(response => {
                    this.messages = response.data;
                    console.log(this.messages)
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                });
        },
        deleteClient(id) {
            axios.delete("http://localhost:8080/api/client/"+ id)
        .then(() => {
                document.location = "http://localhost:8081/client/lister"
                alert("Le client a été supprimé")
            })
                .catch(error => {
                    console.error("Erreur lors de la suppression du client: ", error);
                    alert("Un ou des compte(s) sont associé(s) a ce client, ce qui empêche la suppression")
                });
        }
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