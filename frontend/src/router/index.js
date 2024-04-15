import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue';
import creerClient from "../components/creerClient.vue";
import listeComptes from "../components/listeComptes.vue";
import detailCompte from "../components/detailCompte.vue";
import detailClient from "../components/detailClient.vue";
import creerCompte from "../components/creerCompte.vue";
import listeClients from "../components/listeClients.vue";
import editerClient from "../components/editerClient.vue"
import editerCompte from "../components/editerCompte.vue"
import addSolde from "../components/addSolde.vue"
import transfertClient from "../components/transfertClient.vue"


const routes = [
  {
    path: '/',
    name: "Ma banque",
    component: Home
  },
  {
    path: '/compte/lister',
    name: 'Liste des comptes',
    component: listeComptes
  },
  {
    path: '/compte/:id',
    name: 'Detail du compte',
    component: detailCompte
  },
  {
    path: '/compte/creer',
    name: "Création d'un compte",
    component: creerCompte
  },
  {
    path: '/compte/editer/:id',
    name: "Modification d'un compte",
    component: editerCompte
  },
  {
    path: '/client/lister',
    name: 'Liste des clients',
    component: listeClients
  },
  {
    path: '/client/:id',
    name: "Détail d'un client",
    component: detailClient
  },
  {
    path: '/client/creer',
    name: "Création d'un client",
    component: creerClient
  },
  {
    path: '/client/editer/:id',
    name: "Modification d'un client",
    component: editerClient
  },
  {
    path: '/compte/solde/:id',
    name: "Ajout de solde",
    component: addSolde
  },
  {
    path: '/compte/transfert/:id',
    name: "Virement",
    component: transfertClient
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  document.title = to.name;
  next();
});
export default router
