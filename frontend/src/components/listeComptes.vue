<template>
    <h1> Listes des comptes </h1>
    <v-table>
        <thead>
            <tr class="container">
                <th class="text-end">Identifiant</th>
                <th class="text-center">numero de compte</th>
                <th class="text-center">Solde actuel</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr class="container" v-for="compte in comptes" :key="compte.id">
                <td class="text-end" style="align-content: center">{{compte.id}}</td>
                <td class="text-center">
                    <v-btn style="margin:10px; font-weight: bold" class="text-center" :to="{path : '/compte/'+ compte.id}">{{ compte.numero }}</v-btn>
                </td>
                <td class="text-center" style="align-content: center">{{compte.solde}}</td>
                <td class="text-end">
                    <v-btn style="margin:10px; font-weight: bold" class="text-center btnAdd" :to="{path : '/compte/solde/'+ compte.id}">Ajouter du solde</v-btn>
                    <v-btn style="margin:10px; font-weight: bold" class="text-center btnDo" :to="{path : '/compte/transfert/'+ compte.id}">Effectuer un virement</v-btn>
                </td>
            </tr>
        </tbody>
    </v-table>
</template>
<script>
import axios from "axios";

export default {
    data(){
        return{
            comptes: []
        }
    },
    methods: {
        fetchMessages(){
            axios.get(`http://localhost:8080/api/compte/lister`)
                .then(response => {
                    this.comptes = response.data;
                    console.log(this.comptes)
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
.container{
    display: grid;
    grid-template-columns: 35% 25% 10% 30%;
    margin-top: 10px;
}
.btnAdd{background-color: #F98948 !important; color: white!important;}
.btnDo{background-color: #81C14B !important; color: white!important;}
.btnAdd:hover{background-color: rgba(249, 137, 72, 0.80) !important;}
.btnDo:hover{background-color: rgba(129, 193, 75, 0.80) !important;}
</style>