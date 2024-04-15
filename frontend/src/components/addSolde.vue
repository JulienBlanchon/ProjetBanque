<template>
    <h1>Ajouter du solde a votre compte</h1>

    <v-form @submit.prevent="saveSolde">
        <v-container>
            <v-col>
                <h3>Votre solde actuel est de {{soldeactuel}} €</h3>
                <v-row style="justify-content: center">
                    <label>
                        <input class="input" placeholder="Montant a ajouté" v-model="compte.solde"/>
                    </label>
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

let id=""
export default {
    data(){
        return {
            compte:[],
            soldeactuel:""
        }
    },
    mounted() {
        console.log('mounted called')
    },
    methods: {
        fetchMessages(){
            const url = window.location.pathname;
            id = url.substring(url.lastIndexOf('/') + 1);
            axios.get(`http://localhost:8080/api/compte/${id}`)
                .then(response => {
                    this.compte = response.data;
                    this.soldeactuel = this.compte.solde;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                });

        },
        saveSolde(){
            // Enregistrer le compte avec le client associé
            axios.put('http://localhost:8080/api/compte/addsolde/' + id,this.compte)
                .then(() => {
                    console.log(this.compte)
                    document.location = "http://localhost:8081/compte/lister"
                })
                .catch(error => {
                    console.error('Erreur lors de la création du compte:', error);
                });
        }
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