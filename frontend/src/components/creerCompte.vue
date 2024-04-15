<template>
  <h1>Création de compte</h1>

  <v-form @submit.prevent="saveData">
      <v-container>
          <v-col>
              <v-row style="justify-content: center">
                  <input class="input" v-model="compte.numero" placeholder="Numéro de compte"/>
              </v-row>
              <v-row style="justify-content: center">
                  <select class="input" v-model="compte.id_client" style="color: rgb(20, 163, 128)">
                      <option value="" disabled selected> -- Identifiant du client -- </option>
                      <option v-for="message in messages" :key="message.id" :value="message.id">{{message.id}} : {{message.nom +" "+ message.prenom}}</option>
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
export default {
    data(){
        return {
            result : {},
            compte: {
                numero: '',
                id_client: ''
            },
            messages : []
        }
    },
    mounted() {
        console.log('mounted called')
    },
    methods: {
        fetchMessages(){
            axios.get(`http://localhost:8080/api/client/lister`)
                .then(response => {
                    this.messages = response.data;
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération des messages reçus: ", error);
                    // Gérez l'erreur, par exemple, en affichant un message à l'utilisateur
                });
        },
        saveData(){
            axios.get(`http://localhost:8080/api/client/` + this.compte.id_client)
                .then(response => {
                    // Si le client est trouvé
                    if (response.data) {
                        // Associer le client au compte
                        this.compte.client = response.data;

                        // Enregistrer le compte avec le client associé
                        axios.post('http://localhost:8080/api/compte/creer', this.compte)
                            .then(() => {
                                document.location = "http://localhost:8081/compte/lister"
                                console.log(this.compte)
                            })
                            .catch(error => {
                                console.error('Erreur lors de la création du compte:', error);
                            });
                    } else {
                        alert("Le client spécifié n'existe pas !");
                    }
                })
                .catch(error => {
                    console.error('Erreur lors de la récupération du client:', error);
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