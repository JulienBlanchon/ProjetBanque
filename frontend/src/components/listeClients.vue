<template>
    <h1> Liste des clients </h1>
    <v-table>
        <thead>
            <tr>
                <th class="text-center">Identifiant</th>
                <th class="text-center">Nom</th>
                <th class="text-center">Prénom</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="message in messages" :key="message.id">
                <td class="text-center">{{ message.id}}</td>
                <td><v-btn class="text-center v-btn" style="margin:10px; font-weight: bold;  hover : #0D6D55 " :to="{path : '/client/'+ message.id}">{{ message.nom }}</v-btn></td>
                <td><v-btn class="text-center v-btn" style="margin:10px; font-weight: bold" :to="{path : '/client/'+ message.id}">{{ message.prenom }}</v-btn></td>

            </tr>
        </tbody>
    </v-table>
</template>
<script>
import axios from "axios";

export default {
    data(){
        return{
            messages: []
        }
    },
    methods: {
        fetchMessages(){
            axios.get(`http://localhost:8080/api/client/lister`)
                .then(response => {
                    this.messages = response.data;
                    console.log(this.messages)
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
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
a{
    text-decoration: none;
    color: black;

}

.v-btn:hover{
    background: #0D6D55;
    color:white;
}
</style>