<template>
    <div class="home">
        <h1>Bienvenue sur le système de gestion des comptes clients</h1>
        <p>Cette application permet aux utilisateurs d'effectuer des virement et voir l'etats des comptes clients.</p>

        <ListeCompte/>

    </div>
    <div>
        <v-btn to="/compte/creer">Creer un nouveau compte</v-btn>
    </div>
</template>

<script>

import ListeCompte from '../components/listeComptes.vue';

export default {
    name: 'HomeView',
    components: {
        ListeCompte // Enregistrez le composant MessageList si vous l'utilisez
    },
    // Des méthodes, des propriétés calculées, des hooks de cycle de vie, etc.
}
</script>