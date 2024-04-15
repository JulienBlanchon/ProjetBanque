<template>
    <nav id="navbar">
        <router-link to="/">Ma banque</router-link> |
        <router-link to="/compte/lister">Lister tous les comptes</router-link> |
        <router-link to="/compte/creer">Créer un compte</router-link> |
        <router-link to="/client/lister">Listes tous les clients</router-link> |
        <router-link to="/client/creer">Créer un client</router-link>
    </nav>
</template>

<style scoped>
/* Style général de la barre de navigation */
#navbar {
    background: #14A380;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}

/* Style des liens de la navbar */
nav a, nav button {
    padding: 10px 15px;
    color: #fff;
    text-transform: uppercase;
    transition: background-color 0.3s ease;
}
/* Hover effect pour les liens */
nav a:hover {
    text-decoration: none;
    background-color: #0D6D55;
    border-radius: 5px;
}

a{
    text-decoration: none;
}

/* Style pour le bouton de déconnexion */
nav button {
    background: none;
    border: none;
    cursor: pointer;
}

/* Ajoutez des media queries si nécessaire pour gérer le responsive design de la navbar */
@media (max-width: 768px) {
    #navbar {
        flex-direction: column;
    }
}
</style>