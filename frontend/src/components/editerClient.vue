<template>
    <h1> Modification du client </h1>
    <form @submit.prevent="updateClient()">
        <v-container>
            <v-col>
                <v-row style="justify-content: center">
                    <input class="input" placeholder="Nom de famille" v-model="messages.nom"/>
                </v-row>
                <v-row style="justify-content: center">
                    <input class="input" placeholder="Prénom" v-model="messages.prenom"/>
                </v-row>
                <v-row style="justify-content: center">
                    <v-btn type="submit"> Valider </v-btn>
                </v-row>
            </v-col>
        </v-container>
    </form>
</template>

<script>

import axios from "axios";

export default {
    data(){
        return{
            messages: [],
        }
    },
    methods: {
        fetchMessages() {
            var url = window.location.pathname;
            const id = url.substring(url.lastIndexOf('/') + 1);
            axios.get(`http://localhost:8080/api/client/` + id)
                .then(response => {
                    this.messages = response.data;
                    console.log(this.messages)
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                })
        },
        updateClient() {
            axios.put("http://localhost:8080/api/client/"+ this.messages.id, {
                nom: this.messages.nom,
                prenom: this.messages.prenom
            })
                .then(() => {
                    document.location = "http://localhost:8081/client/" + this.messages.id
                    // Rediriger ou effectuer toute autre action nécessaire après la mise à jour
                })
                .catch(error => {
                    console.error("Erreur lors de la mise à jour du client: ", error);
                });
        },

    },
    created() {
        // Appelez la méthode lors du montage du composant
        this.fetchMessages();
    }
}
</script>
<style>
.input{
    width: 300px;
    min-height: 50px;
    background-color: rgb(20, 163, 128, .25);
    padding: 5px;
    border-radius: 5px;
    margin: 10px;
}

</style>