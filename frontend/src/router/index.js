import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login";
import Main from "../views/Main";
import loggedRoutes from "./loggedRoutes";
import store from "../store/index"
import authRoutes from "./authRoutes";
Vue.use(VueRouter);

  const routes = [
    {
      path: '/',
      component: Main,
      children: loggedRoutes,
      meta: {
        requiresLogin: true,
        guest: false
      }
    },
    {
      path: '/auth/',
      component: Login,
      children: authRoutes,
      meta: {
        requiresLogin: false,
        guest: true
      }
    }

];

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  let requiresLogin = to.matched.some((routeRecord) => routeRecord.meta.requiresLogin);
  let onlyGuest = to.matched.some((routeRecord) => routeRecord.meta.guest);
  let isLogged = store.state.auth.loggedIn;
  if (!requiresLogin && !onlyGuest) {
    next();
  } else if (requiresLogin && !onlyGuest) {
    if(isLogged) next();
    else next('/auth/');
  } else if (!requiresLogin && onlyGuest) {
    if(isLogged) next('/');
    else next();
  } else {
    console.error('An error occurred during routing');
  }
});

export default router
