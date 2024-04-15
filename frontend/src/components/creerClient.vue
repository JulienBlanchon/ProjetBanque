<template>
    <h1>Création de client</h1>

    <v-form @submit.prevent="saveData">
        <v-container>
            <v-col>
                <v-row style="justify-content: center">
                    <input class="input" placeholder="Nom de famille" v-model="client.nom"/>
                </v-row>
                <v-row style="justify-content: center">
                    <input class="input" placeholder="Prénom" v-model="client.prenom"/>
                </v-row>
                <v-row style="justify-content: center">
                    <v-btn type="submit"> Valider </v-btn>
                </v-row>
            </v-col>
        </v-container>
    </v-form>
</template>

<script>
import axios from "axios";
export default {
    data(){
        return {
            client: {
                nom: '',
                prenom: ''
            },
        }
    },
    created() {
    },
    mounted() {
        console.log('mounted called')
    },
    methods: {
        saveData(){
                        // Enregistrer le compte avec le client associé
            axios.post('http://localhost:8080/api/client/creer', this.client, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(() => {
                    document.location = "http://localhost:8081/client/lister"
                })
                .catch(error => {
                    console.error('Erreur lors de la création du compte:', error);
                });
        }
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