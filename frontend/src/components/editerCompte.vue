<template>
    <h1> Modification du compte </h1>
    <form @submit.prevent="updateCompte">
        <v-container>
            <v-col>
                <v-row style="justify-content: center">
                    <input class="input" v-model="compte.numero"/>
                </v-row>
                <v-row style="justify-content: center">
                    <select class="input" v-model="compte.id_client">
                        <option value="" disabled selected>{{clients.id + " : " + clients.nom + clients.prenom }}</option>
                        <option v-for="message in messages" :key="message.id" :value="message.id">{{message.id}} : {{message.nom +" "+ message.prenom}}</option>
                    </select>
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

let id=""
export default {
    data(){
        return{
            result:{},
            compte: {
                numero: '',
                id_client: ''
            },
            clients:[],
            messages:[]
        }
    },
    methods: {
        fetchMessages() {
            var url = window.location.pathname;
            id = url.substring(url.lastIndexOf('/') + 1);
            axios.get(`http://localhost:8080/api/compte/` + id)
                .then(response => {
                    this.compte = response.data;
                    axios.get(`http://localhost:8080/api/client/` + this.compte.id_client)
                        .then(response => {
                            this.clients = response.data

                        })
                        .catch(error => {
                            console.error("Erreur lors de la récupération des messages reçus: ", error);
                            // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                        });
                    axios.get(`http://localhost:8080/api/client/lister`)
                        .then(response => {
                            this.messages = response.data;
                        })
                        .catch(error => {
                            console.error("Erreur lors de la récupération des messages reçus: ", error);
                            // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                        });
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                })
        },
        updateCompte() {
            axios.get(`http://localhost:8080/api/client/` + this.compte.id_client)
                .then(response => {
                    // Si le client est trouvé
                    if (response.data) {
                        // Associer le client au compte
                        this.compte.client = response.data;
                        // Enregistrer le compte avec le client associé
                        axios.put('http://localhost:8080/api/compte/'+ this.compte.id, this.compte)
                            .then(() => {
                                document.location = "http://localhost:8081/compte/" + id
                            })
                            .catch(error => {
                                console.error('Erreur lors de la création du compte:', error);
                            });
                    }
                })
                .catch(error => {
                    console.error('Erreur lors de la récupération du client:', error);
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
.input::placeholder{
    color: rgb(20, 163, 128);
}
.input:focus::placeholder{
    color: transparent;
}

</style>