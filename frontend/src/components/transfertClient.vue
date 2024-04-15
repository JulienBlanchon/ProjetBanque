<template>
    <h1>Ajouter du solde a votre compte</h1>

    <v-form @submit.prevent="transfertMoney">
        <v-container>
            <v-col>
                <h3>Votre solde disponible : {{soldeactuel}} €</h3>
                <v-row style="justify-content: end">
                    <h3 style="align-self: center">De :</h3>
                    <input disabled class="input" v-model="accounts.expediteur" value="{{compte.id}}"/>
                </v-row>
                <v-row style="justify-content: end">
                    <h3 style="align-self: center">Montant :</h3>
                    <input class="input" placeholder="Montant a viré" v-model="accounts.montant"/>
                </v-row>
                <v-row style="justify-content: end">
                    <h3 style="align-self: center">Vers :</h3>
                    <select class="input" v-model="accounts.destinataire" style="color: rgb(20, 163, 128)">
                        <option value="" disabled selected> -- Identifiant du compte -- </option>
                        <option v-for="account in comptes" :key="account.id" :value="account.id">{{account.id}} : {{account.numero}}</option>
                    </select>
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
            soldeactuel:"",
            comptes:[],
            accounts:{
                expediteur:"",
                destinataire:"",
                montant:""
            }
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
                    axios.get(`http://localhost:8080/api/compte/lister`)
                        .then(response => {
                            this.comptes = response.data;
                            this.accounts.expediteur = this.compte.id
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
        transfertMoney(){
            // Enregistrer le compte avec le client associé
            axios.post('http://localhost:8080/api/compte/transfert',this.accounts)
                .then(() => {
                    console.log(this.compte)
                    document.location = "http://localhost:8081/compte/lister"
                })
                .catch(error => {
                    console.error('Erreur lors de la création du compte:', error);
                    alert("Solde insuffisant")
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
.v-container{
    width:25% !important;
}
</style>